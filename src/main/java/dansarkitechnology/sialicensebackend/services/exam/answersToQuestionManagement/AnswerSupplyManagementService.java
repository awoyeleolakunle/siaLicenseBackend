package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.AnswerSuppliedToQuestionRequest;

public interface AnswerSupplyManagementService {

    ApiResponse supplyAnswerToQuestion(AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest);

}
