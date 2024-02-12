package dansarkitechnology.sialicensebackend.services.exam;


import dansarkitechnology.sialicensebackend.data.models.Exam;
import dansarkitechnology.sialicensebackend.data.repositories.ExamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ExamServiceImp implements ExamService{
   private final ExamRepository examRepository;
    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }
    @Override
    public Optional<Exam> findExamById(Long id) {
        return examRepository.findById(id);
    }
}
