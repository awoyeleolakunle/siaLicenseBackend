package dansarkitechnology.sialicensebackend.dtos.response;


import dansarkitechnology.sialicensebackend.data.models.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamShuffledQuestionResponse {
    private Long examId;
    private List<Question> listOfShuffledQuestion;

}
