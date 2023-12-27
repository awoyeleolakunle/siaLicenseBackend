package dansarkitechnology.sialicensebackend.services.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.dtos.request.ApplicantRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class ApplicantRegistrationServiceTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void testTHatApplicantCanRegister(){

        ApplicantRequest applicantRequest  = new ApplicantRequest();
        applicantRequest.setAddress("emailAddress@gmail.com");
        applicantRequest.setPassword("password");
        applicantRequest.setCity("London");
        applicantRequest.setAddress("31 stamford distreet, London, United Kingdom");
        applicantRequest.setPostCode("101101");
        applicantRequest.setFirstName("First Applicant");
        applicantRequest.setLastName("Last Applicant");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/applicant/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(applicantRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}