package dansarkitechnology.sialicensebackend.services.trainingSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.dtos.request.TrainingSessionRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TrainingSessionServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    public void testThatCenterCanCreateTrainingSession(){

        TrainingSessionRequest trainingSessionRequest = new TrainingSessionRequest();
        trainingSessionRequest.setTrainingType("DOOR_SUPERVISION");
        trainingSessionRequest.setTrainingFee(BigDecimal.valueOf(2000));
        trainingSessionRequest.setStartDate(LocalDate.of(2023, 12, 14));
        trainingSessionRequest.setEndDate(LocalDate.of(2023, 12, 25));
        trainingSessionRequest.setNumberOfSlots(5);
        trainingSessionRequest.setCenterEmailAddress("McphersonLicense@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/center/createTrainingSession")
                .content(objectMapper.writeValueAsString(trainingSessionRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}