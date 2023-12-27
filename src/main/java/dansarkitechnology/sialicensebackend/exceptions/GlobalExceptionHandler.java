package dansarkitechnology.sialicensebackend.exceptions;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ApplicantException.class)
    public ResponseEntity<ApiResponse> applicationRegistrationException(ApplicantException applicantRegistrationException){
        ApiResponse apiResponse =  ApiResponse.builder()
                .data(applicantRegistrationException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CenterException.class)
    public ResponseEntity<ApiResponse> centerRegistrationException(CenterException centerRegistrationException){
    return new ResponseEntity<>(ApiResponse.builder()
            .data(centerRegistrationException.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .isSuccessful(false)
            .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ApiResponse> accountException(AccountException accountException){
    return new ResponseEntity<>(ApiResponse.builder()
            .data(accountException.getMessage())
            .httpStatus(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .isSuccessful(false)
            .build(), HttpStatus.BAD_REQUEST);
    }
}
