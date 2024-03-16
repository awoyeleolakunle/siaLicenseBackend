package dansarkitechnology.sialicensebackend.exceptions;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ApplicantException.class)
    public ResponseEntity<ApiResponse> applicationRegistrationException(ApplicantException applicantRegistrationException){
        ApiResponse apiResponse =  ApiResponse.builder()
                .data(applicantRegistrationException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CenterException.class)
    public ResponseEntity<ApiResponse> centerRegistrationException(CenterException centerRegistrationException){
    return new ResponseEntity<>(ApiResponse.builder()
            .data(centerRegistrationException.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .status(HttpStatus.BAD_REQUEST.value())
            .isSuccessful(false)
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TrainingSessionException.class)
    public ResponseEntity<ApiResponse> trainingSessionException(TrainingSessionException trainingSessionException){
        return new ResponseEntity<>(ApiResponse.builder()
                .data(trainingSessionException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ApiResponse> accountException(AccountException accountException){
    return new ResponseEntity<>(ApiResponse.builder()
            .data(accountException.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .status(HttpStatus.BAD_REQUEST.value())
            .isSuccessful(false)
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlogException.class)
    public ResponseEntity<ApiResponse> blogException(BlogException blogException){
        return new ResponseEntity<>(ApiResponse.builder()
                .data(blogException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
}

   // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);

//            String firstErrorMessage = ex.getBindingResult().getAllErrors().stream()
//                    .findFirst()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .orElse(GenerateApiResponse.FILL_ALL_FIELDS);

        return new ResponseEntity<>(ApiResponse.builder()
                .data(GenerateApiResponse.FILL_ALL_FIELDS)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionException.class)
    public ResponseEntity<ApiResponse> QuestionException(QuestionException questionException){
        return new ResponseEntity<>(ApiResponse.builder()
                .data(questionException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ExamException.class)
    public ResponseEntity<ApiResponse> examException(ExamException examException){
        return new ResponseEntity<>(ApiResponse.builder()
                .data(examException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CacheNotFoundException.class)
    public ResponseEntity<ApiResponse> cacheNotFoundException(CacheNotFoundException cacheNotFoundException){
        return new ResponseEntity<>(ApiResponse.builder()
                .data(cacheNotFoundException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
