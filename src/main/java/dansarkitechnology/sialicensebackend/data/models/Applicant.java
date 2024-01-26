package dansarkitechnology.sialicensebackend.data.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
    private String firstName;
    private String lastName;
    private String postCode;
    private String address;
    private String city;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TrainingSession> setOfBookedTrainingSessions;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Exam> listOfExamsTaken;
}
