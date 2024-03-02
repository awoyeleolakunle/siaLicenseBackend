package dansarkitechnology.sialicensebackend.services.exam.examCreation;

import com.fasterxml.jackson.core.JsonProcessingException;
import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Applicant;
import dansarkitechnology.sialicensebackend.data.models.Exam;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.dtos.request.ExamCreationRequest;
import dansarkitechnology.sialicensebackend.dtos.response.ExamShuffledQuestionResponse;
import dansarkitechnology.sialicensebackend.exceptions.ApplicantException;
import dansarkitechnology.sialicensebackend.services.applicant.ApplicantService;
import dansarkitechnology.sialicensebackend.services.exam.examService.ExamService;
import dansarkitechnology.sialicensebackend.services.question.cacheManagement.CacheManagementService;
import dansarkitechnology.sialicensebackend.services.question.questionService.QuestionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ExamCreationServiceImp implements ExamCreationService {
    private final ExamService examService;
    private final ApplicantService applicantService;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;
    private final CacheManagementService cacheManagerService;

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
        List<Question> listOfAllQuestions = getCachedQuestions(examCreationRequest.getExamType());

        Collections.shuffle(listOfAllQuestions);
        cacheManagerService.cacheListOfShuffledQuestions(savedExam.getId(), listOfAllQuestions);

        ExamShuffledQuestionResponse examShuffledQuestionResponse = new ExamShuffledQuestionResponse();
        examShuffledQuestionResponse.setExamId(savedExam.getId());
        examShuffledQuestionResponse.setListOfShuffledQuestion(listOfAllQuestions);
        return examShuffledQuestionResponse;
    }

    private List<Question> getCachedQuestions(String examType) {
        return questionService.findAllQuestionByExamType(examType);
    }
}
