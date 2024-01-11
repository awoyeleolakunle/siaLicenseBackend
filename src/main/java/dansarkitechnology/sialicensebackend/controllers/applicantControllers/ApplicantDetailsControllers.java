package dansarkitechnology.sialicensebackend.controllers.applicantControllers;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.services.applicant.ApplicantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/applicant/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ApplicantDetailsControllers {
    private final ApplicantService applicantService;

    @GetMapping("applicantDetails")
    public ResponseEntity<ApiResponse> findApplicantDetails(@RequestParam String applicantEmailAddress){
        System.out.println("I'm the applicant emailAddress :" + applicantEmailAddress);
        return new ResponseEntity<>(applicantService.findApplicantDetails(applicantEmailAddress), HttpStatus.OK);
    }
}
