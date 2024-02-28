package dansarkitechnology.sialicensebackend.services.question.cachePaginatedQuestions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.data.repositories.ExamRepository;
import jakarta.persistence.Cacheable;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Cacheable
@EnableCaching
@AllArgsConstructor
public class CashedPaginatedQuestionServiceImp implements CashedPaginatedQuestionService {
   // private final RedisTemplate<Long, List<Question>> redisTemplate;
    private final CacheManager cacheManager;
    @Override
    public List<Question> getCashedQuestionsByExamId(Long id) throws JsonProcessingException {
        Cache cache = cacheManager.getCache("Questions");
        System.out.println(cacheManager.getCacheNames().size());
        System.out.println(cache.toString());

            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper == null) {
                System.out.println("I'm the value wrapper returning null");
                return Collections.emptyList();
            }
            Object value = valueWrapper.get();
            if (value instanceof List) {
                return (List<Question>) value;
            }
            return Collections.emptyList();
    }
//            System.out.println("I'm found oooo");
//            System.out.println(cache.getNativeCache().toString());
//            System.out.println("I'm the list from the cache " + cache.get(id, List.class));
//            System.out.println("i'm the id : "+id);
//
//            var list = cache.get(id, List.class);
//            if (list != null) {
//                System.out.println("I'm found again ooo");
//                return list;
//                        //(List<Question>) valueWrapper.get();
//            }
//        }
//        return Collections.emptyList();


    @Override
    public ApiResponse getCachedPaginatedQuestionsByExamId(Long id, int pageNumber, int pageSize) throws JsonProcessingException {
        List<Question> cachedQuestions = getCashedQuestionsByExamId(id);

        System.out.println("I'm the cached questions " + cachedQuestions);
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, cachedQuestions.size());
        if (fromIndex <= toIndex) {
            System.out.println("I'm the paginated list " + cachedQuestions.subList(fromIndex, toIndex));
            return GenerateApiResponse.found(cachedQuestions.subList(fromIndex, toIndex));
        } else {
            return GenerateApiResponse.found(Collections.emptyList());
        }
    }
}
