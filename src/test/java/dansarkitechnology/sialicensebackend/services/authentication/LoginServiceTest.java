package dansarkitechnology.sialicensebackend.services.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.dtos.request.LoginRequest;
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

class LoginServiceTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @SneakyThrows
    public void testThatUserCanLogin(){


        LoginRequest loginRequest  = new LoginRequest();
        loginRequest.setEmailAdress("johnsyke@gmail.com");
        loginRequest.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicense+/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}