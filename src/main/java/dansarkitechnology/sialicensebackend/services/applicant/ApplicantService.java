package dansarkitechnology.sialicensebackend.services.applicant;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Applicant;


public interface ApplicantService {
    Applicant saveApplicant(Applicant applicant);

    Applicant findApplicantByEmailAddress(String emailAddress);

    ApiResponse findApplicantDetails(String applicantEmailAddress);
}
