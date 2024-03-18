package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.Utils.NumericValues;
import dansarkitechnology.sialicensebackend.data.models.Exam;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.dtos.request.AnswerSuppliedToQuestionRequest;
import dansarkitechnology.sialicensebackend.exceptions.ExamException;
import dansarkitechnology.sialicensebackend.exceptions.QuestionException;
import dansarkitechnology.sialicensebackend.services.exam.examService.ExamService;
import dansarkitechnology.sialicensebackend.services.question.questionService.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnswerSupplyManagementServiceImp implements AnswerSupplyManagementService {
    private final ExamService examService;
    private final QuestionService questionService;

    @Override
    @Transactional
    public ApiResponse supplyAnswerToQuestion(AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest) throws QuestionException {

        Optional<Exam> foundExam = examService.findExamById(answerSuppliedToQuestionRequest.getExamId());
        if (foundExam.isEmpty()) throw new ExamException(GenerateApiResponse.NO_EXAMINATION_FOUND);

        Map<Integer, UserAnswerDetails> mappedAnswers = foundExam.get().getUserAnswers();

        disallowOperationIfCorrectOptionHasBeenChosenAndTheCorrectOptionIsChosenAgain(mappedAnswers, answerSuppliedToQuestionRequest);
        mapUserInCorrectAnswerDetailsToMappedAnswers(answerSuppliedToQuestionRequest, mappedAnswers, foundExam.get());
        mapUserCorrectAnswerDetailsToMappedAnswers(answerSuppliedToQuestionRequest, mappedAnswers, foundExam.get());


        return GenerateApiResponse.ok(GenerateApiResponse.ANSWER_SUCCESSFULLY_RECORDED);
    }

    private void mapUserInCorrectAnswerDetailsToMappedAnswers(AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest, Map<Integer, UserAnswerDetails> mappedAnswers, Exam exam) throws QuestionException {
        Optional<Question> foundQuestion = questionService.findQuestionById((long) answerSuppliedToQuestionRequest.getQuestionId());
        if (foundQuestion.isEmpty()) throw new QuestionException(GenerateApiResponse.NO_SUCH_QUESTION);

        UserAnswerDetails userAnswerDetails = mappedAnswers.get(answerSuppliedToQuestionRequest.getQuestionId());
        if (userAnswerDetails == null) {
            userAnswerDetails = new UserAnswerDetails();
        }

        if (!foundQuestion.get().getCorrectOption().equalsIgnoreCase(answerSuppliedToQuestionRequest.getAnswerSupplied()) &&
                userAnswerDetails.isCorrectOptionChosen()
        ) {

            userAnswerDetails.setUserAnswer(answerSuppliedToQuestionRequest.getAnswerSupplied());
            userAnswerDetails.setCorrectOptionChosen(false);
            exam.setTotalScore(exam.getTotalScore() - NumericValues.ONE);
            mappedAnswers.put(answerSuppliedToQuestionRequest.getQuestionId(), userAnswerDetails);
            exam.setUserAnswers(mappedAnswers);
            examService.save(exam);
        }
        userAnswerDetails.setUserAnswer(answerSuppliedToQuestionRequest.getAnswerSupplied());
        userAnswerDetails.setCorrectOptionChosen(false);
        mappedAnswers.put(answerSuppliedToQuestionRequest.getQuestionId(), userAnswerDetails);
        exam.setUserAnswers(mappedAnswers);
        examService.save(exam);

}
    private void mapUserCorrectAnswerDetailsToMappedAnswers
            (AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest, Map<Integer, UserAnswerDetails> mappedAnswers, Exam exam) throws QuestionException {

        Optional<Question> foundQuestion = questionService.findQuestionById((long) answerSuppliedToQuestionRequest.getQuestionId());
        if (foundQuestion.isEmpty()) throw new QuestionException(GenerateApiResponse.NO_SUCH_QUESTION);

        UserAnswerDetails foundUserAnswerDetails = mappedAnswers.get(answerSuppliedToQuestionRequest.getQuestionId());
        if (foundUserAnswerDetails == null) {
            foundUserAnswerDetails = new UserAnswerDetails();
        }
        if (foundQuestion.get().getCorrectOption().equalsIgnoreCase(answerSuppliedToQuestionRequest.getAnswerSupplied())
        ) {
            foundUserAnswerDetails.setUserAnswer(answerSuppliedToQuestionRequest.getAnswerSupplied());
            foundUserAnswerDetails.setCorrectOptionChosen(true);
            mappedAnswers.put(answerSuppliedToQuestionRequest.getQuestionId(), foundUserAnswerDetails);
            exam.setUserAnswers(mappedAnswers);
            exam.setTotalScore(exam.getTotalScore() + NumericValues.ONE);
            examService.save(exam);
        }
    }

    private void disallowOperationIfCorrectOptionHasBeenChosenAndTheCorrectOptionIsChosenAgain
            (Map<Integer, UserAnswerDetails> mappedAnswers, AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest) {
        if(mappedAnswers.containsKey(answerSuppliedToQuestionRequest.getQuestionId())){
          UserAnswerDetails foundUserAnswerDetails=  mappedAnswers.get(answerSuppliedToQuestionRequest.getQuestionId());
          if(foundUserAnswerDetails.getUserAnswer().equalsIgnoreCase(answerSuppliedToQuestionRequest.getAnswerSupplied())&&
                  (foundUserAnswerDetails.isCorrectOptionChosen())) throw new ExamException(GenerateApiResponse.VALUE_INPUT_CURRENTLY_CHOSEN);
        }
}
}
