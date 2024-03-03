package dansarkitechnology.sialicensebackend.services.exam.examService;


import dansarkitechnology.sialicensebackend.Utils.NumericValues;
import dansarkitechnology.sialicensebackend.data.models.Exam;
import dansarkitechnology.sialicensebackend.data.repositories.ExamRepository;
import dansarkitechnology.sialicensebackend.services.exam.examService.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExamServiceImp implements ExamService {
   private final ExamRepository examRepository;
    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }
    @Override
    public Optional<Exam> findExamById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    @Scheduled(fixedRate = NumericValues.NINETY * NumericValues.SIXTY* NumericValues.ONE_THOUSAND)
    @Transactional
    public void clearExpiredExams() {
        LocalDateTime time = LocalDateTime.now().minusMinutes(NumericValues.NINETY);
        examRepository.deleteAllByTimeTakenIsBefore(time);
    }
}
