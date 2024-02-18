package dansarkitechnology.sialicensebackend.services.question;

import dansarkitechnology.sialicensebackend.data.enums.ExamType;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.data.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionServiceImp implements QuestionService{
    private final QuestionRepository questionRepository;
    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question findQuestionByQuestion(String question) {
        return questionRepository.findByQuestion(question);
    }

    @Override
    public Optional<Question> findQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Cacheable(value = "Questions", key = "#examId")
    @Override
    public List<Question> findAllQuestionByExamType(String examType, Long examId) {
        return questionRepository.findAllByExamType(ExamType.valueOf(examType));
    }
}
