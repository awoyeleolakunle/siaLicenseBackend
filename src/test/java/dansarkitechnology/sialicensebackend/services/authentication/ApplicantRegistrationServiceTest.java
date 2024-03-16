package dansarkitechnology.sialicensebackend.services.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.data.enums.Roles;
import dansarkitechnology.sialicensebackend.data.models.User;
import dansarkitechnology.sialicensebackend.dtos.request.ApplicantRequest;
import dansarkitechnology.sialicensebackend.services.userService.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@SpringBootTest
@AutoConfigureMockMvc
class ApplicantRegistrationServiceTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;


    @Autowired
   private PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Test
    public void testTHatApplicantCanRegister(){

        ApplicantRequest applicantRequest  = new ApplicantRequest();
        applicantRequest.setEmailAddress("emailAddress@gmail.com");
        applicantRequest.setPassword("password");
        applicantRequest.setCity("London");
        applicantRequest.setAddress("31 stamford district, London, United Kingdom");
        applicantRequest.setPostCode("101101");
        applicantRequest.setFirstName("First Applicant");
        applicantRequest.setLastName("Last Applicant");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/applicant/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(applicantRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    void registerAdmin(){

        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.ADMIN);
        User user = new User();
        user.setRoles(roles);
        String password = passwordEncoder.encode("password");
        user.setPassword(password);
        user.setEmailAddress("adminEmailAddress@gmail.com");
        user.setRegistrationDate(LocalDateTime.now());
        user.setFirstName("Doe");
        user.setLastName("John");
        user.setMiddleName("Peter");
        userService.save(user);
    }
}