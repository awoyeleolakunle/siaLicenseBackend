package dansarkitechnology.sialicensebackend.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreationRequest {
    @NotEmpty
    @NotBlank
    private String question;
    @NotEmpty
    @NotBlank
    private String examType;
    @NotEmpty
    private List<String> listOfOptions;
    @NotEmpty
    @NotBlank
    private String correctOption;

}
