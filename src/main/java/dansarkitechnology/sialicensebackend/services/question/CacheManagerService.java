package dansarkitechnology.sialicensebackend.services.question;

import dansarkitechnology.sialicensebackend.data.models.Question;

import java.util.List;

public interface CacheManagerService {


    List<Question> cacheListOfShuffledQuestions(Long id, List<Question> listOfShuffledQuestion);

    List<Question> getCachedShuffledQuestions(Long id);
}
