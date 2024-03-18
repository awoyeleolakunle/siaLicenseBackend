package dansarkitechnology.sialicensebackend.controllers.authenticationControllers.registrationControllers;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.CenterRequest;
import dansarkitechnology.sialicensebackend.exceptions.AccountException;
import dansarkitechnology.sialicensebackend.exceptions.CenterException;
import dansarkitechnology.sialicensebackend.services.authentication.CenterRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/center/")
@CrossOrigin(origins= "*")
@AllArgsConstructor

public class CenterRegistrationController {
    private final CenterRegistrationService registrationService;
    @PostMapping("register")
public ResponseEntity<ApiResponse> registerCenter(@RequestBody @Valid CenterRequest centerRequest) throws CenterException, AccountException {
        return new ResponseEntity<>(registrationService.registerCenter(centerRequest), HttpStatus.CREATED);
    }
}
