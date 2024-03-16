package dansarkitechnology.sialicensebackend.controllers.questionController;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.QuestionCreationRequest;
import dansarkitechnology.sialicensebackend.exceptions.QuestionException;
import dansarkitechnology.sialicensebackend.services.question.questionCreation.QuestionCreationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sialicence+/question/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class QuestionCreationController {

    private final QuestionCreationService questionCreationService;

    @PostMapping("questionCreation")
    public ResponseEntity<ApiResponse> createQuestion(@RequestBody @Valid QuestionCreationRequest questionCreationRequest) throws QuestionException {
        return new ResponseEntity<>(questionCreationService.createQuestion(questionCreationRequest), HttpStatus.CREATED);
    }
}
