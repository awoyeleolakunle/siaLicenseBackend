package dansarkitechnology.sialicensebackend.controllers;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import dansarkitechnology.sialicensebackend.dtos.request.BookTrainingSessionRequest;
import dansarkitechnology.sialicensebackend.dtos.request.TrainingSessionRequest;
import dansarkitechnology.sialicensebackend.exceptions.ApplicantException;
import dansarkitechnology.sialicensebackend.exceptions.CenterException;
import dansarkitechnology.sialicensebackend.exceptions.TrainingSessionException;
import dansarkitechnology.sialicensebackend.services.bookTraining.BookTrainingService;
import dansarkitechnology.sialicensebackend.services.trainingSession.TrainingSessionService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/applicant/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class BookTrainingController {

    private final BookTrainingService bookTrainingService;


    @PostMapping("bookTraining")
    public ResponseEntity<ApiResponse> bookingTraining(@RequestBody BookTrainingSessionRequest bookTrainingSessionRequest) throws CenterException, ApplicantException, TrainingSessionException {
        return new ResponseEntity<>(bookTrainingService.bookTraining(bookTrainingSessionRequest), HttpStatus.CREATED);
    }
}
