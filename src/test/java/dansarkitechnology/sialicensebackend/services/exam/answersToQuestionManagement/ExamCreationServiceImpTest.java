package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;


import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.dtos.request.ExamCreationRequest;
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
class ExamCreationServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testThatExamCanBeCreated() throws Exception {
        ExamCreationRequest examCreationRequest = new ExamCreationRequest();
        examCreationRequest.setExamType("COMMON_UNIT");
        examCreationRequest.setApplicantEmailAddress("emailAddress@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/exam/examCreation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(examCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}