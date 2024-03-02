package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ExamRepository extends JpaRepository<Exam, Long> {
  void deleteAllByTimeTakenIsBefore(LocalDateTime timeTaken);
}
