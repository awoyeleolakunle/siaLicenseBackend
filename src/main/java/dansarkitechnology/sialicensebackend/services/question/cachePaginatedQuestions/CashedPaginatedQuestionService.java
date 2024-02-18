package dansarkitechnology.sialicensebackend.services.question.cachePaginatedQuestions;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Question;

import java.util.List;

public interface CashedPaginatedQuestionService {
    List<Question> getCashedQuestionsByExamId(Long examId);

    ApiResponse getCachedPaginatedQuestionsByExamId(Long examId, int pageNumber, int pageSize );

}
