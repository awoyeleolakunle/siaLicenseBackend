package dansarkitechnology.sialicensebackend.controllers.answerSupplyManagementControllers;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.AnswerSuppliedToQuestionRequest;
import dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement.AnswerSupplyManagementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/exam/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AnswerSupplyManagementController {
    private final AnswerSupplyManagementService answerSupplyManagementService;

    @PostMapping("answerSupplyToQuestion")
    public ResponseEntity<ApiResponse> supplyAnswerToQuestion(@RequestBody @Valid AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest){
        return new ResponseEntity<>(answerSupplyManagementService.supplyAnswerToQuestion(answerSuppliedToQuestionRequest), HttpStatus.OK);
    }
}
