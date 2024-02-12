package dansarkitechnology.sialicensebackend.data.models;



import dansarkitechnology.sialicensebackend.Utils.MapAttributeConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> shuffledQuestions;

    @Convert(converter = MapAttributeConverter.class)
    @ElementCollection
    private Map<Integer, String> userAnswers;
    private String applicantEmailAddress;
    private String applicantLastName;
    private String applicantFirstName;
    private LocalDateTime timeTaken;
    private int totalScore;
}
