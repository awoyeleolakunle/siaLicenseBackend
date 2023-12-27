package dansarkitechnology.sialicensebackend.services.trainingSession;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import dansarkitechnology.sialicensebackend.dtos.request.TrainingSessionRequest;

import java.util.Optional;

public interface TrainingSessionService {
    ApiResponse createTrainingSession(TrainingSessionRequest trainingSessionRequest);

    Optional<TrainingSession> findTrainingSessionById(Long trainingId);

}
