package dansarkitechnology.sialicensebackend.Utils;


import org.springframework.http.HttpStatus;

public class GenerateApiResponse {


    public static final String BEARER = "Bearer ";
    public static final String CENTER_ALREADY_EXIST = " Center with this details already exists";
    public static final String TRAINING_SESSION_CREATED = "Training session created successfully";
    public static final String APPLICANT_ALREADY_EXIST ="Applicant with this information already exists" ;
    public static final String TRAINING_SESSION_NOT_FOUND = "Training session has expired";
    public static final String CENTER_NOT_FOUND = "Center does not exist";
    public static final String APPLICANT_NOT_FOUND = "Applicant with this information can not be found";
    public static final String TRAINING_SUCCESSFULLY_BOOKED = "You have successfully booked your training";

    public static final String DELETED_SUCCESSFULLY = "Deleted successfully";
    public static final String BLOG_POST_NOT_FOUND = "Blog can not be found";
    public static final String STATUS_UPDATED_SUCCESSFULLY = "Status updated successfully";
    public static final String POST_SUCCESSFULLY_CREATED = "You have successfully created a post";
    public static final String FILL_ALL_FIELDS = "Kindly fill all fields correctly" ;
    public static final String QUESTION_SUCCESSFULLY_CREATED =" Question created successfully" ;
    public static final String QUESTION_ALREADY_EXIST ="Question with same content " ;
    public static final String NO_EXAMINATION_FOUND = "Examination record cannot be found";
    public static final String ANSWER_SUCCESSFULLY_RECORDED = "Answer successfully recorded";
    public static final String VALUE_INPUT_CURRENTLY_CHOSEN  = "Option currently chosen" ;
    public static final String NO_SUCH_QUESTION = "No such question";
    public static final String NO_CACHE_FOUND = "No data found" ;
    public static final String TRAINING_ALREADY_BOOKED = "You have previously booked for this training";
    public static final String INVALID_DETAILS = "Invalid Credentials";
    public static final String SESSION_TIME_OUT ="Kindly login to your account" ;


    public static ApiResponse createdResponse(Object data){
        return ApiResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .status(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .data(data)
                .build();
    }

    public static ApiResponse alreadyExist(Object message) {
        return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .data(message)
                .build();
    }

    public static ApiResponse found(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse deleteSuccessful(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse updateSuccessful(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse blogPostNotFound(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse UpdateStatus(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse ok(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .status(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }
}
