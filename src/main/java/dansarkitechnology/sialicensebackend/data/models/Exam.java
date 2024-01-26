package dansarkitechnology.sialicensebackend.data.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private String applicantEmailAddress;
    private String applicantLastName;
    private String applicantFirstName;
    private LocalDateTime timeTaken;
    private int totalScore;
}
