package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingSessionRepository  extends JpaRepository<TrainingSession, Long> {
}
