package dansarkitechnology.sialicensebackend.services.question;

import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.data.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
