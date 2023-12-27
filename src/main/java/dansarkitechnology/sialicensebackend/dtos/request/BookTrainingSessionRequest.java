package dansarkitechnology.sialicensebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookTrainingSessionRequest {
    private Long trainingId;
    private String applicantEmailAddress;
    private Long centerId;
}
