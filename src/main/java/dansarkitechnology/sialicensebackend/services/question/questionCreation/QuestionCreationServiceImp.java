package dansarkitechnology.sialicensebackend.services.question.questionCreation;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.enums.ExamType;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.dtos.request.QuestionCreationRequest;
import dansarkitechnology.sialicensebackend.exceptions.QuestionException;
import dansarkitechnology.sialicensebackend.services.question.questionService.QuestionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionCreationServiceImp implements QuestionCreationService{
    private final QuestionService questionService;
    private final ModelMapper modelMapper;
    public ApiResponse createQuestion (QuestionCreationRequest questionCreationRequest) throws QuestionException {
        handleDuplicateCreation(questionCreationRequest);
        Question question = modelMapper.map(questionCreationRequest, Question.class);
        question.setExamType(ExamType.valueOf(questionCreationRequest.getExamType().toUpperCase()));
        questionService.save(question);
        return GenerateApiResponse.createdResponse(GenerateApiResponse.QUESTION_SUCCESSFULLY_CREATED);
    }

    private void handleDuplicateCreation(QuestionCreationRequest questionCreationRequest) throws QuestionException {
        Question existingQuestion = questionService.findQuestionByQuestion(questionCreationRequest.getQuestion());
        if(existingQuestion!=null) throw new QuestionException(GenerateApiResponse.QUESTION_ALREADY_EXIST);
    }
}
