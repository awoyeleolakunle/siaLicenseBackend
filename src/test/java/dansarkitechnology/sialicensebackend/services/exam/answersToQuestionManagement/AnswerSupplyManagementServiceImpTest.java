package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;
import com.fasterxml.jackson.databind.ObjectMapper;

import dansarkitechnology.sialicensebackend.dtos.request.AnswerSuppliedToQuestionRequest;
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
class AnswerSupplyManagementServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testThatAnApplicantCanSupplyAnAnswerToAQuestion() throws Exception {

        AnswerSuppliedToQuestionRequest answerSuppliedToQuestionRequest = new AnswerSuppliedToQuestionRequest();
        answerSuppliedToQuestionRequest.setExamId(1L);
        answerSuppliedToQuestionRequest.setQuestionId(1);
        answerSuppliedToQuestionRequest.setAnswerSupplied("Kano");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/exam/answerSupplyToQuestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(answerSuppliedToQuestionRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}