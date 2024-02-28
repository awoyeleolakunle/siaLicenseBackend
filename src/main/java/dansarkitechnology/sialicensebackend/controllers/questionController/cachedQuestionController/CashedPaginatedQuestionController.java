package dansarkitechnology.sialicensebackend.controllers.questionController.cachedQuestionController;


import com.fasterxml.jackson.core.JsonProcessingException;
import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.dtos.request.PaginationRequest;
import dansarkitechnology.sialicensebackend.services.question.cachePaginatedQuestions.CashedPaginatedQuestionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/question/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CashedPaginatedQuestionController {


    private final CashedPaginatedQuestionService cashedPaginatedQuestionService;
    @GetMapping("paginatedQuestion")
    public ResponseEntity<ApiResponse> getCashedPaginatedQuestionByExamId( @Valid @RequestParam  Long examId,  @RequestParam @Valid int pageNumber, @RequestParam @Valid int pageSize) throws JsonProcessingException {
       return new ResponseEntity<>(cashedPaginatedQuestionService.getCachedPaginatedQuestionsByExamId(examId, pageNumber, pageSize), HttpStatus.OK);
    }
}
