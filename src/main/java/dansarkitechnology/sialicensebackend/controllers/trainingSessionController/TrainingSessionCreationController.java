package dansarkitechnology.sialicensebackend.controllers.trainingSessionController;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.TrainingSessionRequest;
import dansarkitechnology.sialicensebackend.services.trainingSession.TrainingSessionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("api/v1/sialicence+/center/")
@CrossOrigin(origins= "*")
@AllArgsConstructor
public class TrainingSessionCreationController {
    private final TrainingSessionService trainingSessionService;

    @PostMapping("createTrainingSession")
    public ResponseEntity<ApiResponse> createTrainingSession(@RequestBody @Valid TrainingSessionRequest trainingSessionRequest){
        System.out.println("I'm the training type : "+ trainingSessionRequest.getTrainingType());
        System.out.println("I'm the start date : " + trainingSessionRequest.getStartDate());
        System.out.println("I'm the end Date : " + trainingSessionRequest.getEndDate());
        return new ResponseEntity<>(trainingSessionService.createTrainingSession(trainingSessionRequest), HttpStatus.CREATED);
    }
}
