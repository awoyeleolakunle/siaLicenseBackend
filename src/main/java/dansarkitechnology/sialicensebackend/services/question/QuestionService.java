package dansarkitechnology.sialicensebackend.services.question;

import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.exceptions.QuestionException;

import java.util.List;

public interface QuestionService {
    Question save(Question question);
    Question findQuestionByQuestion (String question);

    List<Question> findAllQuestionByExamType(String examType);
}
