package dansarkitechnology.sialicensebackend.controllers.applicantControllers;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.ApplicantRequest;
import dansarkitechnology.sialicensebackend.services.authentication.ApplicantRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/applicant/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class ApplicantRegisterController {
    private final ApplicantRegistrationService applicantRegistration;

    @SneakyThrows
    @PostMapping("register")
    public ResponseEntity<ApiResponse> registerApplicant(@RequestBody @Valid ApplicantRequest applicantRequest){
        return new ResponseEntity<>(applicantRegistration.registerApplicant(applicantRequest), HttpStatus.CREATED);
    }
}
