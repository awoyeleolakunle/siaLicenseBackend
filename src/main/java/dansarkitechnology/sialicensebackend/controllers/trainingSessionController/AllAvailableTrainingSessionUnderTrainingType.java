package dansarkitechnology.sialicensebackend.controllers.trainingSessionController;


import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import dansarkitechnology.sialicensebackend.services.trainingSession.TrainingSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sialicence+/trainingSession/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AllAvailableTrainingSessionUnderTrainingType {

    private final TrainingSessionService trainingSessionService;


    @GetMapping ("allAvailableTrainingSessionUnderTrainingType")
    public ResponseEntity<List<TrainingSession>> fetchAllAvailableTrainingSessionUnderTrainingType(@RequestParam String trainingType){
        return new ResponseEntity<>(trainingSessionService.findAllAvailableTrainingSessionUnderAtTrainingType(trainingType), HttpStatus.OK);
    }
}
