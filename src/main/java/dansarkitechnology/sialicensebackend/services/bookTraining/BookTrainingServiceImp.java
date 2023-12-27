package dansarkitechnology.sialicensebackend.services.bookTraining;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Applicant;
import dansarkitechnology.sialicensebackend.data.models.BookedTraining;
import dansarkitechnology.sialicensebackend.data.models.Center;
import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import dansarkitechnology.sialicensebackend.dtos.request.BookTrainingSessionRequest;
import dansarkitechnology.sialicensebackend.exceptions.ApplicantException;
import dansarkitechnology.sialicensebackend.exceptions.CenterException;
import dansarkitechnology.sialicensebackend.exceptions.TrainingSessionException;
import dansarkitechnology.sialicensebackend.services.applicant.ApplicantService;
import dansarkitechnology.sialicensebackend.services.bookedTrainings.BookedTrainingSessionService;
import dansarkitechnology.sialicensebackend.services.center.CenterService;
import dansarkitechnology.sialicensebackend.services.trainingSession.TrainingSessionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor

public class BookTrainingServiceImp implements BookTrainingService{
    private final TrainingSessionService trainingSessionService;
    private final CenterService centerService;
    private final ApplicantService applicantService;

    private final BookedTrainingSessionService bookedTrainingSessionService;

    @Override
    @Transactional
    public ApiResponse bookTraining(BookTrainingSessionRequest bookTrainingSessionRequest) throws TrainingSessionException, CenterException, ApplicantException {

        Applicant applicant = applicantService.findApplicantByEmailAddress(bookTrainingSessionRequest.getApplicantEmailAddress());
        Applicant validatedApplicant =  validateApplicant(applicant);
        Optional<Center> center = centerService.findCenterById(bookTrainingSessionRequest.getCenterId());
        Center validatedCenter = validateCenter(center);


        Optional<TrainingSession> trainingSession = trainingSessionService.findTrainingSessionById(bookTrainingSessionRequest.getTrainingId());
       TrainingSession validatedTrainingSession =  validateTrainingSession(trainingSession);

        addTrainingSessionToTheSetOfApplicantBookedTrainings(validatedApplicant, validatedTrainingSession);

        BookedTraining savedBookedTraining = createNewBookedTraining(validatedTrainingSession, validatedApplicant,  validatedCenter);

        addTrainingSessionToTheSetOfCenterBookedTrainingSession( validatedCenter, savedBookedTraining);

        return GenerateApiResponse.createdResponse(GenerateApiResponse.TRAINING_SUCCESSFULLY_BOOKED);
    }

    private TrainingSession validateTrainingSession(Optional<TrainingSession> trainingSession) throws TrainingSessionException {
        if(trainingSession.isEmpty()) throw new TrainingSessionException(GenerateApiResponse.TRAINING_SESSION_NOT_FOUND);
        return trainingSession.get();
    }

    private Applicant validateApplicant(Applicant applicant) throws ApplicantException {
        if(applicant==null) throw new ApplicantException(GenerateApiResponse.APPLICANT_NOT_FOUND);
        return applicant;
    }

    private Center validateCenter(Optional<Center> center) throws CenterException {
        if(center.isEmpty()) throw new CenterException(GenerateApiResponse.CENTER_NOT_FOUND);
        return center.get();
    }

    private BookedTraining createNewBookedTraining(TrainingSession trainingSession, Applicant applicant, Center center){
        BookedTraining bookedTraining = new BookedTraining();
        bookedTraining.setTrainingSession(trainingSession);
        bookedTraining.setApplicant(applicant);
        bookedTraining.setCenter(center);
        return bookedTrainingSessionService.saveBookedTrainingSession(bookedTraining);
    }

    private void addTrainingSessionToTheSetOfApplicantBookedTrainings(Applicant applicant, TrainingSession trainingSession){
        Set<TrainingSession> setOfApplicantTrainingSession = applicant.getSetOfBookedTrainingSessions();
        setOfApplicantTrainingSession.add(trainingSession);
        applicant.setSetOfBookedTrainingSessions(new HashSet<>(setOfApplicantTrainingSession));
        applicantService.saveApplicant(applicant);
    }
    private void addTrainingSessionToTheSetOfCenterBookedTrainingSession(Center center, BookedTraining bookedTraining){
        Set<BookedTraining> setOfCenterTrainingSession = center.getSetOfBookedTrainings();
        setOfCenterTrainingSession.add(bookedTraining);
        center.setSetOfBookedTrainings(new HashSet<>(setOfCenterTrainingSession));
        centerService.saveCenter(center);
    }
}
