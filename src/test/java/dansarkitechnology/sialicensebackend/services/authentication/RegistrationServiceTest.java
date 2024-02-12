package dansarkitechnology.sialicensebackend.services.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.dtos.request.CenterRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc

class RegistrationServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void testThatCenterCanRegister(){

        CenterRequest centerRequest = new CenterRequest();
        centerRequest.setCenterName("Mcpherson License");
        centerRequest.setEmailAddress("McphersonLicense@gmail.com");
        centerRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/center/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(centerRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}