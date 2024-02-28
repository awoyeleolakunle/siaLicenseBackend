package dansarkitechnology.sialicensebackend.services.question.cachePaginatedQuestions;

import com.fasterxml.jackson.core.JsonProcessingException;
import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Question;

import java.util.List;

public interface CashedPaginatedQuestionService {
    List<Question> getCashedQuestionsByExamId(Long examId) throws JsonProcessingException;

    ApiResponse getCachedPaginatedQuestionsByExamId(Long examId, int pageNumber, int pageSize ) throws JsonProcessingException;

}
