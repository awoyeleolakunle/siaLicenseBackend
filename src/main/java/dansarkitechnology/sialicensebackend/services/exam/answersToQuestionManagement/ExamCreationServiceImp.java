package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Applicant;
import dansarkitechnology.sialicensebackend.data.models.Exam;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.dtos.request.ExamCreationRequest;
import dansarkitechnology.sialicensebackend.dtos.response.ExamShuffledQuestionResponse;
import dansarkitechnology.sialicensebackend.exceptions.ApplicantException;
import dansarkitechnology.sialicensebackend.services.applicant.ApplicantService;
import dansarkitechnology.sialicensebackend.services.exam.ExamCreationService;
import dansarkitechnology.sialicensebackend.services.exam.ExamService;
import dansarkitechnology.sialicensebackend.services.question.QuestionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ExamCreationServiceImp implements ExamCreationService {
    private final ExamService examService;
    private final ApplicantService applicantService;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    private final CacheManager cacheManager;
    private  final ObjectMapper objectMapper;

   // private final RedisTemplate<Long, List<Question>> redisTemplate;


    @Override
    public ApiResponse createExam(ExamCreationRequest examCreationRequest) throws ApplicantException, JsonProcessingException {
        verifyApplicant(examCreationRequest.getApplicantEmailAddress());
        ExamShuffledQuestionResponse examShuffledQuestionResponse = mappedExam(examCreationRequest);
        return GenerateApiResponse.createdResponse(examShuffledQuestionResponse);
    }
    private void verifyApplicant(String applicantEmailAddress) throws ApplicantException {
        Applicant foundUser = applicantService.findApplicantByEmailAddress(applicantEmailAddress);
        if(foundUser==null) throw new ApplicantException(GenerateApiResponse.APPLICANT_NOT_FOUND);
    }
    private ExamShuffledQuestionResponse mappedExam (ExamCreationRequest examCreationRequest) throws JsonProcessingException {

        Exam exam = modelMapper.map(examCreationRequest, Exam.class);
        exam.setTimeTaken(LocalDateTime.now());
        Exam savedExam = examService.save(exam);
        List<Question> listOfAllQuestions = getCachedQuestions(examCreationRequest.getExamType(), savedExam.getId());

        Cache cache = cacheManager.getCache("Questions");
        if (cache != null) {
            cache.put(savedExam.getId(), listOfAllQuestions);
            System.out.println("I'm the list of cashed questions : "+ listOfAllQuestions);
            System.out.println("I'm I'm not null");
            System.out.println("i'm the saved exam id : "+ savedExam.getId());
            System.out.println("I'm the saved confirmtaion " + cache.get(savedExam.getId(), List.class));
        }
        assert cache != null;
        System.out.println("i'm the to string cached question: " + objectMapper.writeValueAsString(Objects.requireNonNull(cache.get(savedExam.getId(), List.class))).toString());
        Collections.shuffle(listOfAllQuestions);
        ExamShuffledQuestionResponse examShuffledQuestionResponse = new ExamShuffledQuestionResponse();
        examShuffledQuestionResponse.setExamId(savedExam.getId());
        examShuffledQuestionResponse.setListOfShuffledQuestion(listOfAllQuestions);
        return examShuffledQuestionResponse;
//        Exam exam = modelMapper.map(examCreationRequest, Exam.class);
//        exam.setTimeTaken(LocalDateTime.now());
//        Exam savedExam = examService.save(exam);
//        List<Question> listOfAllQuestions = getCachedQuestions(examCreationRequest.getExamType(), savedExam.getId());
//
//        Collections.shuffle(listOfAllQuestions);
//        ExamShuffledQuestionResponse examShuffledQuestionResponse = new ExamShuffledQuestionResponse();
//        examShuffledQuestionResponse.setExamId(savedExam.getId());
//        examShuffledQuestionResponse.setListOfShuffledQuestion(listOfAllQuestions);
//        return examShuffledQuestionResponse;
    }


    private List<Question> getCachedQuestions(String examType, Long id) {
        return questionService.findAllQuestionByExamType(examType, id);

//        List<Question> cachedQuestions = redisTemplate.opsForValue().get(examId);
//        if (cachedQuestions == null) {
//            cachedQuestions = questionService.findAllQuestionByExamType(examType); // Adjust this according to your QuestionService
//            redisTemplate.opsForValue().set(examId, cachedQuestions);
//        }
//        return cachedQuestions;
    }
}
