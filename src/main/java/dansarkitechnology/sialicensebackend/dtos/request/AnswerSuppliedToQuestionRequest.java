package dansarkitechnology.sialicensebackend.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSuppliedToQuestionRequest {
    @NonNull
    private Long examId;
   @NonNull
    private int questionId;
   @NotBlank
   @NotEmpty
    private String answerSupplied;
}
