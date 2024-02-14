package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.Utils.UserAnswerDetails;
import dansarkitechnology.sialicensebackend.data.models.Exam;
import dansarkitechnology.sialicensebackend.dtos.request.AnswerSuppliedToQuestionRequest;
import dansarkitechnology.sialicensebackend.exceptions.ExamException;
import dansarkitechnology.sialicensebackend.services.exam.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnswerSupplyManagementServiceImp implements AnswerSupplyManagementService{
    private final ExamService examService;
    @Override
    @Transactional
    public ApiResponse supplyAnswerToQuestion(AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest) {

     Optional<Exam> foundExam = examService.findExamById(answerSuppliedToQuestionRequest.getExamId());
      if(foundExam.isEmpty()) throw new ExamException(GenerateApiResponse.NO_EXAMINATION_FOUND);

      Map<Integer, UserAnswerDetails> mappedAnswers = foundExam.get().getUserAnswers();

      disallowOperationIfCorrectOptionHasBeenChosenAndTheCorrectOptionIsChosenAgain(mappedAnswers, answerSuppliedToQuestionRequest);
      mapUserCorrectAnswerDetailsToMappedAnswers(answerSuppliedToQuestionRequest, mappedAnswers, foundExam.get());
      mapUserInCorrectAnswerDetailsToMappedAnswers( answerSuppliedToQuestionRequest, mappedAnswers, foundExam.get());

        return GenerateApiResponse.ok(GenerateApiResponse.ANSWER_SUCCESSFULLY_RECORDED);
    }

    private void mapUserInCorrectAnswerDetailsToMappedAnswers(AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest, Map<Integer, UserAnswerDetails> mappedAnswers, Exam exam) {
        if (!exam.getShuffledQuestions().get(answerSuppliedToQuestionRequest.getQuestionIndex()).getCorrectOption()
                .equalsIgnoreCase(answerSuppliedToQuestionRequest.getAnswerSupplied()) &&
                mappedAnswers.get(answerSuppliedToQuestionRequest.getQuestionIndex())
                        .isCorrectOptionChosen()) {
            UserAnswerDetails userAnswerDetails = new UserAnswerDetails();
            userAnswerDetails.setUserAnswer(answerSuppliedToQuestionRequest.getAnswerSupplied());
            userAnswerDetails.setCorrectOptionChosen(false);
            mappedAnswers.put(answerSuppliedToQuestionRequest.getQuestionIndex(), userAnswerDetails);
            exam.setUserAnswers(mappedAnswers);
            exam.setTotalScore(exam.getTotalScore() - 1);
            examService.save(exam);
        } else {

            if (!exam.getShuffledQuestions().get(answerSuppliedToQuestionRequest.getQuestionIndex()).getCorrectOption()
                    .equalsIgnoreCase(answerSuppliedToQuestionRequest.getAnswerSupplied())) {
                UserAnswerDetails userAnswerDetails = new UserAnswerDetails();
                userAnswerDetails.setUserAnswer(answerSuppliedToQuestionRequest.getAnswerSupplied());
                userAnswerDetails.setCorrectOptionChosen(false);
                mappedAnswers.put(answerSuppliedToQuestionRequest.getQuestionIndex(), userAnswerDetails);
                exam.setUserAnswers(mappedAnswers);
                examService.save(exam);
            }
        }
    }

    private void mapUserCorrectAnswerDetailsToMappedAnswers
            (AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest, Map<Integer, UserAnswerDetails> mappedAnswers, Exam exam) {

        if(mappedAnswers.containsKey(answerSuppliedToQuestionRequest.getQuestionIndex())){
            UserAnswerDetails foundUserAnswerDetails =mappedAnswers.get(answerSuppliedToQuestionRequest.getQuestionIndex());

            if(exam.getShuffledQuestions().get(answerSuppliedToQuestionRequest.getQuestionIndex())
                    .getCorrectOption().equalsIgnoreCase(answerSuppliedToQuestionRequest.getAnswerSupplied())){
                foundUserAnswerDetails.setUserAnswer(answerSuppliedToQuestionRequest.getAnswerSupplied());
                foundUserAnswerDetails.setCorrectOptionChosen(true);
                mappedAnswers.put(answerSuppliedToQuestionRequest.getQuestionIndex(), foundUserAnswerDetails);
                exam.setUserAnswers(mappedAnswers);
                exam.setTotalScore(exam.getTotalScore()+1);
                examService.save(exam);
            }
        }
    }

    private void disallowOperationIfCorrectOptionHasBeenChosenAndTheCorrectOptionIsChosenAgain
            (Map<Integer, UserAnswerDetails> mappedAnswers, AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest) {

        if(mappedAnswers.containsKey(answerSuppliedToQuestionRequest.getQuestionIndex())){
          UserAnswerDetails foundUserAnswerDetails=  mappedAnswers.get(answerSuppliedToQuestionRequest.getQuestionIndex());
        if(foundUserAnswerDetails.getUserAnswer().equalsIgnoreCase(answerSuppliedToQuestionRequest.getAnswerSupplied())&&
                  (foundUserAnswerDetails.isCorrectOptionChosen())) throw new ExamException(GenerateApiResponse.VALUE_INPUT_CURRENTLY_CHOSEN);
        }
}
}
