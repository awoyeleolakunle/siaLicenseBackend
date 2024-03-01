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
        examCreationRequest.setExamType("DOOR_SUPERVISION_IN_THE_PRIVATE_SECURITY_INDUSTRY");
        examCreationRequest.setApplicantEmailAddress("emailAddress@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sialicence+/exam/examCreation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(examCreationRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
        @Test
        public void testThatACachedPaginatedQuestionCanBeFetched() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sialicence+/question/paginatedQuestion")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("id", " 2402")
                            .param("pageNumber", "1")
                            .param("pageSize", "10"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
}

