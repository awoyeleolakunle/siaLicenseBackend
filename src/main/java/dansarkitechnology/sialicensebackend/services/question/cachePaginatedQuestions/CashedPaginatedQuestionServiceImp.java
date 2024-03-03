package dansarkitechnology.sialicensebackend.services.question.cachePaginatedQuestions;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.Utils.NumericValues;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.exceptions.CacheNotFoundException;
import dansarkitechnology.sialicensebackend.services.question.cacheManagement.CacheManagementService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@EnableCaching
@AllArgsConstructor
public class CashedPaginatedQuestionServiceImp implements CashedPaginatedQuestionService {
    private final CacheManagementService cacheManagerService;
    @Override
    public List<Question> getCashedQuestionsByExamId(Long id) {
        return cacheManagerService.getCachedShuffledQuestions(id);
            }

    @Override
    public ApiResponse getCachedPaginatedQuestionsByExamId(Long id, int pageNumber, int pageSize) {
        List<Question> cachedQuestions = getCashedQuestionsByExamId(id);
        if(cachedQuestions== null) throw new CacheNotFoundException(GenerateApiResponse.NO_CACHE_FOUND);
        int fromIndex = (pageNumber - NumericValues.ONE) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, cachedQuestions.size());
        if (fromIndex <= toIndex) {
            return GenerateApiResponse.found(cachedQuestions.subList(fromIndex, toIndex));
        } else {
            return GenerateApiResponse.found(Collections.emptyList());
        }
    }
}