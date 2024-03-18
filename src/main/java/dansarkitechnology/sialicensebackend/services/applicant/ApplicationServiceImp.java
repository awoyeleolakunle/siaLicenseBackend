package dansarkitechnology.sialicensebackend.services.applicant;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Applicant;
import dansarkitechnology.sialicensebackend.data.repositories.ApplicantRepository;
;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationServiceImp implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    @Override
    public Applicant saveApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Applicant findApplicantByEmailAddress(String emailAddress) {
        return applicantRepository.findByUser_EmailAddress(emailAddress);
    }

    @Override
    public ApiResponse findApplicantDetails(String applicantEmailAddress) {
        Applicant foundApplicantDetails = findApplicantByEmailAddress(applicantEmailAddress);
        return GenerateApiResponse.found(foundApplicantDetails);
    }
}
