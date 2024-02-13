package dansarkitechnology.sialicensebackend.services.exam.answersToQuestionManagement;

import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Applicant;
import dansarkitechnology.sialicensebackend.data.models.Exam;
import dansarkitechnology.sialicensebackend.data.models.Question;
import dansarkitechnology.sialicensebackend.dtos.request.ExamCreationRequest;
import dansarkitechnology.sialicensebackend.exceptions.ApplicantException;
import dansarkitechnology.sialicensebackend.services.UserService;
import dansarkitechnology.sialicensebackend.services.applicant.ApplicantService;
import dansarkitechnology.sialicensebackend.services.exam.ExamCreationService;
import dansarkitechnology.sialicensebackend.services.exam.ExamService;
import dansarkitechnology.sialicensebackend.services.question.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ExamCreationServiceImp implements ExamCreationService {
    private final ExamService examService;
    private final ApplicantService applicantService;
    private final QuestionService questionService;
    @Override
    public ApiResponse createExam(ExamCreationRequest examCreationRequest) throws ApplicantException {
        verifyApplicant(examCreationRequest.getApplicantEmailAddress());
        Exam savedExam = mappedExam(examCreationRequest);
        return GenerateApiResponse.createdResponse(savedExam);
    }
    private void verifyApplicant(String applicantEmailAddress) throws ApplicantException {
        Applicant foundUser = applicantService.findApplicantByEmailAddress(applicantEmailAddress);
        if(foundUser==null) throw new ApplicantException(GenerateApiResponse.APPLICANT_NOT_FOUND);
    }
    private Exam mappedExam (ExamCreationRequest examCreationRequest){
        Exam exam = new Exam();
        List<Question> listOfAllQuestions = questionService.findAllQuestionByExamType(examCreationRequest.getExamType().toUpperCase().trim());
        Collections.shuffle(listOfAllQuestions);
        exam.setShuffledQuestions(new ArrayList<>(listOfAllQuestions));
        exam.setTimeTaken(LocalDateTime.now());
        exam.setApplicantEmailAddress(examCreationRequest.getApplicantEmailAddress());
        return examService.save(exam);
    }
}
