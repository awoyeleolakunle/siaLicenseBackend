package dansarkitechnology.sialicensebackend.services.question;

import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.exceptions.QuestionException;

public interface QuestionService {
    Question save(Question question);
    Question findQuestionByQuestion (String question);
}
