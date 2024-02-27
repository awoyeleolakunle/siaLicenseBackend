package dansarkitechnology.sialicensebackend.data.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dansarkitechnology.sialicensebackend.Utils.UserAnswerDetails;
import jakarta.persistence.*;
;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Long id;

    // This is a map that contains the userAnswerDetails as value and questionID as key.
    private String userAnswers;
    private String applicantEmailAddress;
    private String applicantLastName;
    private String applicantFirstName;
    private LocalDateTime timeTaken;
    private int totalScore;

    public Map<Integer, UserAnswerDetails> getUserAnswers() {
        if (userAnswers == null || userAnswers.isEmpty()) {
            return new HashMap<>();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(userAnswers, new TypeReference<Map<Integer, UserAnswerDetails>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    public void setUserAnswers(Map<Integer, UserAnswerDetails> userAnswers) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.userAnswers = objectMapper.writeValueAsString(userAnswers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
