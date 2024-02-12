package dansarkitechnology.sialicensebackend.services.exam;

import dansarkitechnology.sialicensebackend.data.models.Exam;

import java.util.Optional;

public interface ExamService {
    Exam save(Exam exam);

   Optional<Exam> findExamById(Long id);

}
