package dansarkitechnology.sialicensebackend.services.bookedTrainings;


import dansarkitechnology.sialicensebackend.data.models.BookedTraining;
import dansarkitechnology.sialicensebackend.data.repositories.BookedTrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookedTrainingSessionServiceImp implements BookedTrainingSessionService {

    private final BookedTrainingRepository bookedTrainingRepository;
    @Override
    public BookedTraining saveBookedTrainingSession(BookedTraining bookedTraining) {
        return bookedTrainingRepository.save(bookedTraining);
    }
}
