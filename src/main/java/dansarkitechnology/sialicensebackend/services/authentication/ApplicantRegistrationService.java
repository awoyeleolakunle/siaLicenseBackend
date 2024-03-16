package dansarkitechnology.sialicensebackend.services.authentication;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.enums.Roles;
import dansarkitechnology.sialicensebackend.data.models.Applicant;
import dansarkitechnology.sialicensebackend.data.models.User;
import dansarkitechnology.sialicensebackend.dtos.request.ApplicantRequest;
import dansarkitechnology.sialicensebackend.exceptions.ApplicantException;
import dansarkitechnology.sialicensebackend.security.JwtService;
import dansarkitechnology.sialicensebackend.services.userService.UserService;
import dansarkitechnology.sialicensebackend.services.applicant.ApplicantService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
public class ApplicantRegistrationService {

    private final ApplicantService applicantService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;


    @Transactional
    public ApiResponse registerApplicant(ApplicantRequest applicantRequest) throws ApplicantException {

        boolean isRegistered = userService.findUserByEmailAddress(applicantRequest.getEmailAddress())!=null;
        if(isRegistered) throw new ApplicantException(GenerateApiResponse.APPLICANT_ALREADY_EXIST);

        User newlyBuiltUser = buildNewUser(applicantRequest);
        Applicant newlyBuiltApplicant = buildNewApplicant(newlyBuiltUser, applicantRequest);

        UserDetails userDetails = userDetailsService.loadUserByUsername(newlyBuiltApplicant.getUser().getEmailAddress());
        String jwt = jwtService.generateToken(userDetails);
        return GenerateApiResponse.createdResponse(jwt);

    }

    private Applicant buildNewApplicant(User newlyBuiltUser, ApplicantRequest applicantRequest) {
        Applicant applicant = modelMapper.map(applicantRequest, Applicant.class);
        applicant.setUser(newlyBuiltUser);
        return applicantService.saveApplicant(applicant);
    }

    private User buildNewUser(ApplicantRequest applicantRequest) {
        User user = new User();
        user.setEmailAddress(applicantRequest.getEmailAddress());
        user.setFirstName(applicantRequest.getFirstName());
        user.setLastName(applicantRequest.getLastName());
        user.setPassword(passwordEncoder.encode(applicantRequest.getPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.APPLICANT);
        user.setRoles(new HashSet<>(roles));
        return userService.save(user);
    }
}
