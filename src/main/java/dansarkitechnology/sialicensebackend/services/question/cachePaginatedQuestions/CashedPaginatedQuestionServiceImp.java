package dansarkitechnology.sialicensebackend.services.question.cachePaginatedQuestions;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Question;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CashedPaginatedQuestionServiceImp implements CashedPaginatedQuestionService {
    private final RedisTemplate<Long, List<Question>> redisTemplate;
    private final CacheManager cacheManager;
    @Override
    public List<Question> getCashedQuestionsByExamId(Long examId) {
        Cache cache = cacheManager.getCache("Questions");
        if (cache != null) {
            System.out.println("I'm found oooo");
            System.out.println(cache.getNativeCache());
            Cache.ValueWrapper valueWrapper = cache.get(examId);
            if (valueWrapper != null) {
                System.out.println("I'm found again ooo");
                return (List<Question>) valueWrapper.get();
            }
        }
        return Collections.emptyList();
    }


    @Override
    public ApiResponse getCachedPaginatedQuestionsByExamId(Long examId, int pageNumber, int pageSize) {
        List<Question> cachedQuestions = getCashedQuestionsByExamId(examId);

        System.out.println("I'm the cached questions " + cachedQuestions);
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, cachedQuestions.size());
        if (fromIndex < toIndex) {
            System.out.println("I'm the paginated list " + cachedQuestions.subList(fromIndex, toIndex));
            return GenerateApiResponse.found(cachedQuestions.subList(fromIndex, toIndex));
        } else {
            return GenerateApiResponse.found(Collections.emptyList());
        }
    }
}
