package dansarkitechnology.sialicensebackend.Utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDetails {
    private String userAnswer;
    private boolean isCorrectOptionChosen;
}
