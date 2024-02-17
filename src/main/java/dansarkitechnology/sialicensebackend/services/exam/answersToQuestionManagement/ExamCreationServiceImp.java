package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;

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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ExamCreationServiceImp implements ExamCreationService {
    private final ExamService examService;
    private final ApplicantService applicantService;
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse createExam(ExamCreationRequest examCreationRequest) throws ApplicantException {
        verifyApplicant(examCreationRequest.getApplicantEmailAddress());
        ExamShuffledQuestionResponse examShuffledQuestionResponse = mappedExam(examCreationRequest);
        return GenerateApiResponse.createdResponse(examShuffledQuestionResponse);
    }
    private void verifyApplicant(String applicantEmailAddress) throws ApplicantException {
        Applicant foundUser = applicantService.findApplicantByEmailAddress(applicantEmailAddress);
        if(foundUser==null) throw new ApplicantException(GenerateApiResponse.APPLICANT_NOT_FOUND);
    }
    private ExamShuffledQuestionResponse mappedExam (ExamCreationRequest examCreationRequest) {
        Exam exam = modelMapper.map(examCreationRequest, Exam.class);
        exam.setTimeTaken(LocalDateTime.now());
        List<Question> listOfAllQuestions = questionService.findAllQuestionByExamType(examCreationRequest.getExamType());
        Exam savedExam = examService.save(exam);
        Collections.shuffle(listOfAllQuestions);
        ExamShuffledQuestionResponse examShuffledQuestionResponse = new ExamShuffledQuestionResponse();
        examShuffledQuestionResponse.setExamId(savedExam.getId());
        examShuffledQuestionResponse.setListOfShuffledQuestion(listOfAllQuestions);
        return examShuffledQuestionResponse;
    }
}
