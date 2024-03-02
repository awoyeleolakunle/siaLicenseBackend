package dansarkitechnology.sialicensebackend.services.question.questionService;

import dansarkitechnology.sialicensebackend.data.enums.ExamType;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.data.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionServiceImp implements QuestionService {
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

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Question> findAllQuestionByExamType(String examType) {
        return questionRepository.findAllByExamType(ExamType.valueOf(examType.toUpperCase()));
    }
}
