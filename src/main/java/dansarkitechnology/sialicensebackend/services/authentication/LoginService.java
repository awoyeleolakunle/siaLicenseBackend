package dansarkitechnology.sialicensebackend.services.authentication;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Token;
import dansarkitechnology.sialicensebackend.dtos.request.LoginRequest;
import dansarkitechnology.sialicensebackend.exceptions.AccountException;
import dansarkitechnology.sialicensebackend.security.JwtService;
import dansarkitechnology.sialicensebackend.services.tokenService.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    private final TokenService tokenService;
    public ApiResponse login(LoginRequest loginRequest) throws AccountException {
        try{
        authenticate(loginRequest);
        }catch(AuthenticationException e){throw new AccountException(GenerateApiResponse.ACCOUNT_NOT_FOUND);}
        UserDetails userDetails =userDetailsService.loadUserByUsername(loginRequest.getEmailAddress());
        String jwt = jwtService.generateToken(userDetails);
        saveToken(jwt, loginRequest.getEmailAddress());
        return GenerateApiResponse.found("Bearer "+jwt);
    }

    private void authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));
    }

    private void saveToken(String jwt, String emailAddress) {
        Token token = Token.builder()
                .jwt(jwt)
                .isExpired(false)
                .isRevoked(false)
                .userEmailAddress(emailAddress)
                .build();
        tokenService.saveToken(token);
    }
}
