package dansarkitechnology.sialicensebackend.controllers.examController;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.ExamCreationRequest;
import dansarkitechnology.sialicensebackend.exceptions.ApplicantException;
import dansarkitechnology.sialicensebackend.services.exam.ExamCreationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/exam/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ExamCreationController {
    private final ExamCreationService examCreationService;
    @PostMapping("examCreation")
    public ResponseEntity<ApiResponse> createExam(@RequestBody @Valid ExamCreationRequest examCreationRequest) throws ApplicantException {
        return new ResponseEntity<>(examCreationService.createExam(examCreationRequest), HttpStatus.CREATED);
    }
}
