package dansarkitechnology.sialicensebackend.controllers.authenticationControllers.registrationControllers;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.CenterRequest;
import dansarkitechnology.sialicensebackend.services.authentication.CenterRegistrationService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/center/")
@CrossOrigin(origins= "*")
@AllArgsConstructor

public class CenterRegistrationController {

    private final CenterRegistrationService registrationService;
@SneakyThrows
    @PostMapping("register")
public ResponseEntity<ApiResponse> registerCenter(@RequestBody CenterRequest centerRequest){
        return new ResponseEntity<>(registrationService.registerCenter(centerRequest), HttpStatus.CREATED);
    }
}
