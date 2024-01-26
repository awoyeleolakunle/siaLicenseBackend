package dansarkitechnology.sialicensebackend.services.exam;

import dansarkitechnology.sialicensebackend.data.models.Exam;

public interface ExamService {
    Exam save(Exam exam);

   Exam findExamById(Long id);

}
