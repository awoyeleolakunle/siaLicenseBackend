package dansarkitechnology.sialicensebackend.services.question.questionCreation;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.QuestionCreationRequest;
import dansarkitechnology.sialicensebackend.exceptions.QuestionException;

public interface QuestionCreationService {
    ApiResponse createQuestion (QuestionCreationRequest questionCreationRequest) throws QuestionException;
}