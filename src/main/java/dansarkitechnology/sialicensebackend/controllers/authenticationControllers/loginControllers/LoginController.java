package dansarkitechnology.sialicensebackend.controllers.authenticationControllers.loginControllers;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.LoginRequest;
import dansarkitechnology.sialicensebackend.exceptions.AccountException;
import dansarkitechnology.sialicensebackend.services.authentication.LoginService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) throws AccountException {
        return new ResponseEntity<>(loginService.login(loginRequest), HttpStatus.OK);
    }
}
