package dansarkitechnology.sialicensebackend.services.authentication;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.LoginRequest;
import dansarkitechnology.sialicensebackend.exceptions.AccountException;
import dansarkitechnology.sialicensebackend.security.JwtService;
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
        System.out.println("i was called ");
        authenticate(loginRequest);
        UserDetails userDetails =userDetailsService.loadUserByUsername(loginRequest.getEmailAddress());
        if(userDetails==null) throw new AccountException(GenerateApiResponse.ACCOUNT_NOT_FOUND);
        String jwt = jwtService.generateToken(userDetails);
        System.out.println("I'm the jwt : " + jwt);
        return GenerateApiResponse.found(jwt);

    }

    private void authenticate(LoginRequest loginRequest) {
        System.out.println("I'm the login email : "+ loginRequest.getEmailAddress());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));
    }
}
