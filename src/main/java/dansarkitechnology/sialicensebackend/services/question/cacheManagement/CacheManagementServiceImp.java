package dansarkitechnology.sialicensebackend.services.question.cacheManagement;

import dansarkitechnology.sialicensebackend.data.models.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheManagementServiceImp implements CacheManagementService {
    private final CacheManager cacheManager;

    @Override
    @Cacheable(value = "shuffleListOfQuestion", key = "#id")
    public List<Question> cacheListOfShuffledQuestions(Long id, List<Question> listOfShuffledQuestion) {
        return listOfShuffledQuestion;
    }

    @Override
    @Cacheable(value = "shuffleListOfQuestion", key = "#id")
    public List<Question> getCachedShuffledQuestions(Long id) {
        Cache cache = cacheManager.getCache("shuffleListOfQuestion");
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper != null) {
                return (List<Question>) valueWrapper.get();
            }
        }
                return Collections.emptyList();
    }
}
