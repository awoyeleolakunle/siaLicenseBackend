package dansarkitechnology.sialicensebackend.services.question.cachePaginatedQuestions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class CashedPaginatedQuestionServiceImpTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testThatACachedPaginatedQuestionCanBeFetched() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/sialicence+/question/paginatedQuestion")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "2252")
                .param("pageNumber", "1")
                .param("pageSize", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}