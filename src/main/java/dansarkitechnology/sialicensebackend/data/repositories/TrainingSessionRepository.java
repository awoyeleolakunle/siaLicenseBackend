package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.enums.TrainingType;
import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TrainingSessionRepository  extends JpaRepository<TrainingSession, Long> {

    List<TrainingSession> findByTrainingTypeAndStartDateAfter(TrainingType trainingType, LocalDate startDate);
}
