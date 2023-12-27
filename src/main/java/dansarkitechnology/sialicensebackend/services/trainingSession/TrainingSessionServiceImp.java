package dansarkitechnology.sialicensebackend.services.trainingSession;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.enums.TrainingType;
import dansarkitechnology.sialicensebackend.data.models.Center;
import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import dansarkitechnology.sialicensebackend.data.repositories.TrainingSessionRepository;
import dansarkitechnology.sialicensebackend.dtos.request.TrainingSessionRequest;
import dansarkitechnology.sialicensebackend.services.center.CenterService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class TrainingSessionServiceImp implements TrainingSessionService {

    private final ModelMapper modelMapper;
    private final TrainingSessionRepository trainingSessionRepository;
    private final CenterService centerService;

    @Override
    @Transactional
    public ApiResponse createTrainingSession(TrainingSessionRequest trainingSessionRequest) {

        TrainingSession trainingSession = modelMapper.map(trainingSessionRequest, TrainingSession.class);
        trainingSession.setTrainingType(TrainingType.valueOf(trainingSessionRequest.getTrainingType().toUpperCase()));
        TrainingSession savedTrainingSession = trainingSessionRepository.save(trainingSession);

        Center center = centerService.findCenterByEmailAddress(trainingSessionRequest.getCenterEmailAddress());
        Set<TrainingSession> setOfTrainingSession = center.getSetOfTrainingSession();
        setOfTrainingSession.add(savedTrainingSession);
        center.setSetOfTrainingSession(new HashSet<>(setOfTrainingSession));
        centerService.saveCenter(center);
        return GenerateApiResponse.createdResponse(GenerateApiResponse.TRAINING_SESSION_CREATED);
    }

    @Override
    public Optional<TrainingSession> findTrainingSessionById(Long trainingId) {
        return trainingSessionRepository.findById(trainingId);
    }
}

