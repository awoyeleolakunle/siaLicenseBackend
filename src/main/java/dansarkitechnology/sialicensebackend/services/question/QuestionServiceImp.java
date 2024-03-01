package dansarkitechnology.sialicensebackend.services.question;

import dansarkitechnology.sialicensebackend.data.enums.ExamType;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.data.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class QuestionServiceImp implements QuestionService{
    private final QuestionRepository questionRepository;

    private final CacheManager cacheManager;
    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question findQuestionByQuestion(String question) {
        return questionRepository.findByQuestion(question);
    }

    @Override
    public Optional<Question> findQuestionById(Long id) {
        return questionRepository.findById(id);
    }

//    @CachePut(value = "Questions", key = "#id")
    @Override
    public List<Question> findAllQuestionByExamType(String examType) {
//    Cache cache = cacheManager.getCache("Questions");
//    if (cache != null) {
//        Cache.ValueWrapper valueWrapper = cache.get(id);
//        if (valueWrapper != null && valueWrapper.get() instanceof List) {
//            return (List<Question>) valueWrapper.get();
//        }
//    }
    List<Question> questions = questionRepository.findAllByExamType(ExamType.valueOf(examType.toUpperCase()));
  //  Collections.shuffle(questions);
//    assert cache != null;
//    cache.put(id, questions);
//    System.out.println("I'm the shuffled list "+ questions);
        return questions;
    }

    @Override
    public List<Question> retrieveCachedQuestionsByExamType(Long id) {
        Cache cache = cacheManager.getCache("Questions");
        if (cache != null) {
            System.out.println("I'm in retrieve and I'm the cache and i'm present ");
            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper != null && valueWrapper.get() instanceof List) {
                return (List<Question>) valueWrapper.get();
            }
        }
        return Collections.emptyList();
    }
}
