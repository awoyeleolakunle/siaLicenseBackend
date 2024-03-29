package dansarkitechnology.sialicensebackend.services.question.questionService;

import dansarkitechnology.sialicensebackend.data.models.Question;
import jakarta.persistence.Cacheable;


import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Question save(Question question);
    Question findQuestionByQuestion (String question);

    Optional<Question> findQuestionById(Long id);

    List<Question> findAllQuestionByExamType(String examType);

}
