package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Exam;
import dansarkitechnology.sialicensebackend.dtos.request.AnswerSuppliedToQuestionRequest;
import dansarkitechnology.sialicensebackend.exceptions.ExamException;
import dansarkitechnology.sialicensebackend.services.exam.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnswerSupplyManagementServiceImp implements AnswerSupplyManagementService{
    private final ExamService examService;
    @Override
    public ApiResponse supplyAnswerToQuestion(AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest) {

     Optional<Exam> foundExam = examService.findExamById(answerSuppliedToQuestionRequest.getExamId());
      if(foundExam.isEmpty()) throw new ExamException(GenerateApiResponse.NO_EXAMINATION_FOUND);
      Map<Integer, String> mappedAnswers = foundExam.get().getUserAnswers();
        if (mappedAnswers.containsKey(answerSuppliedToQuestionRequest.getQuestionIndex())) {
            mappedAnswers.put(answerSuppliedToQuestionRequest.getQuestionIndex(),
                    answerSuppliedToQuestionRequest.getAnswerSupplied());
        } else {
            mappedAnswers.put(answerSuppliedToQuestionRequest.getQuestionIndex(),
                    answerSuppliedToQuestionRequest.getAnswerSupplied());
        }
        foundExam.get().setUserAnswers(new HashMap<>(mappedAnswers));
        examService.save(foundExam.get());
        return GenerateApiResponse.ok(GenerateApiResponse.ANSWER_SUCCESSFULLY_RECORDED);

    }
}
