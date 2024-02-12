package dansarkitechnology.sialicensebackend.data.models;


import dansarkitechnology.sialicensebackend.data.enums.ExamType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private List<String> listOfOptions;
    private String correctOption;
    @Enumerated(EnumType.STRING)
    private ExamType examType;
}
