package dansarkitechnology.sialicensebackend.services.authentication;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.User;
import dansarkitechnology.sialicensebackend.dtos.request.LoginRequest;
import dansarkitechnology.sialicensebackend.exceptions.AccountException;
import dansarkitechnology.sialicensebackend.security.JwtService;
import dansarkitechnology.sialicensebackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;



    public ApiResponse login(LoginRequest loginRequest) throws AccountException {
        authenticate(loginRequest);
        UserDetails userDetails =userDetailsService.loadUserByUsername(loginRequest.getEmailAdress());
        if(userDetails==null) throw new AccountException(GenerateApiResponse.ACCOUNT_NOT_FOUND);
        String jwt = jwtService.generateToken(userDetails);
        System.out.println("I'm the jwt : " + jwt);
        return GenerateApiResponse.found(jwt);

    }

    private void authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAdress(), loginRequest.getPassword()));
    }
}
