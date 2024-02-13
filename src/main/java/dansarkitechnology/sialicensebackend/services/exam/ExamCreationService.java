package dansarkitechnology.sialicensebackend.services.exam;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.ExamCreationRequest;
import dansarkitechnology.sialicensebackend.exceptions.ApplicantException;

public interface ExamCreationService {
    ApiResponse createExam(ExamCreationRequest examCreationRequest) throws ApplicantException;
}
