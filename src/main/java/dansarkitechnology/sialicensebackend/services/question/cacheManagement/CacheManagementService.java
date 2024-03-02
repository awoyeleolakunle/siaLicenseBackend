package dansarkitechnology.sialicensebackend.services.question.cacheManagement;

import dansarkitechnology.sialicensebackend.data.models.Question;

import java.util.List;

public interface CacheManagementService {


    List<Question> cacheListOfShuffledQuestions(Long id, List<Question> listOfShuffledQuestion);

    List<Question> getCachedShuffledQuestions(Long id);
}
