package dansarkitechnology.sialicensebackend.services.question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.dtos.request.QuestionCreationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class QuestionCreationServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testThatQuestionCreationCanBeCreated() throws Exception {

        QuestionCreationRequest questionCreationRequest = new QuestionCreationRequest();
        questionCreationRequest.setQuestion("What is the last book of the bible ");
        questionCreationRequest.setExamType("DOOR_SUPERVISION_IN_THE_PRIVATE_SECURITY_INDUSTRY");
        questionCreationRequest.setCorrectOption("Revelation");
        List<String> listOfOptions = new ArrayList<>();
        listOfOptions.add("Mathew");
        listOfOptions.add("Luke");
        listOfOptions.add("Revelation");
        listOfOptions.add("Genesis");
        questionCreationRequest.setListOfOptions(new ArrayList<>(listOfOptions));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/question/questionCreation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(questionCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}