package dansarkitechnology.sialicensebackend.services.authentication;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.enums.Roles;
import dansarkitechnology.sialicensebackend.data.models.Center;
import dansarkitechnology.sialicensebackend.data.models.Token;
import dansarkitechnology.sialicensebackend.data.models.User;
import dansarkitechnology.sialicensebackend.dtos.request.CenterRequest;
import dansarkitechnology.sialicensebackend.exceptions.AccountException;
import dansarkitechnology.sialicensebackend.exceptions.CenterException;
import dansarkitechnology.sialicensebackend.security.JwtService;
import dansarkitechnology.sialicensebackend.services.tokenService.TokenService;
import dansarkitechnology.sialicensebackend.services.userService.UserService;
import dansarkitechnology.sialicensebackend.services.center.CenterService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CenterRegistrationService {

    private final CenterService centerService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final UserDetailsService userDetailsService;

    @Transactional
    public ApiResponse registerCenter(CenterRequest centerRequest) throws CenterException, AccountException {
      try {
          boolean userIsFound = userService.findUserByEmailAddress(centerRequest.getEmailAddress()) != null;
          if (userIsFound) throw new CenterException(GenerateApiResponse.CENTER_ALREADY_EXIST);
      }catch(RuntimeException e){
          throw new CenterException(GenerateApiResponse.CENTER_ALREADY_EXIST);
      }
        User builtUser = buildUser(centerRequest);
        Center builtCenter = buildCenter(builtUser, centerRequest);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(centerRequest.getEmailAddress(), centerRequest.getPassword()));
        }catch (AuthenticationException e){
            throw new AccountException(GenerateApiResponse.INVALID_DETAILS);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(builtCenter.getUser().getEmailAddress());
        String jwt = jwtService.generateToken(userDetails);
        saveToken(jwt, centerRequest.getEmailAddress());
        return  GenerateApiResponse.createdResponse(GenerateApiResponse.BEARER+jwt);
    }
    private Center buildCenter(User builtUser, CenterRequest centerRequest) {
        Center center = new Center();
        center.setCenterName(centerRequest.getCenterName());
        center.setCity(centerRequest.getCity());
        center.setAddress(centerRequest.getAddress());
        center.setPostCode(centerRequest.getPostCode());
        center.setUser(builtUser);
        return centerService.saveCenter(center);
    }

    private User buildUser(CenterRequest centerRequest) {
        User user = new User();
        user.setEmailAddress(centerRequest.getEmailAddress());
        user.setPassword(passwordEncoder.encode(centerRequest.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        Set < Roles> roles = new HashSet<>();
        roles.add(Roles.CENTER);
        user.setRoles(new HashSet<>(roles));
        return userService.save(user);
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
