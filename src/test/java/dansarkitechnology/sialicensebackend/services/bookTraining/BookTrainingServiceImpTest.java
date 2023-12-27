package dansarkitechnology.sialicensebackend.services.bookTraining;

import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.data.models.BookedTraining;
import dansarkitechnology.sialicensebackend.dtos.request.BookTrainingSessionRequest;
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
class BookTrainingServiceImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @SneakyThrows
    @Test
    public void testThatApplicantCanBookTraining(){

        BookTrainingSessionRequest bookTrainingSessionRequest = new BookTrainingSessionRequest();
        bookTrainingSessionRequest.setTrainingId(1L);
        bookTrainingSessionRequest.setCenterId(1L);
        bookTrainingSessionRequest.setApplicantEmailAddress("emailAddress@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/applicant/bookTraining")
                .content(objectMapper.writeValueAsString(bookTrainingSessionRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

}