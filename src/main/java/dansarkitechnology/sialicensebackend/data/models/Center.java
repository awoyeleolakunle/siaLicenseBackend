package dansarkitechnology.sialicensebackend.data.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String centerName;
    @OneToOne
    private User user;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TrainingSession> setOfTrainingSession;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<BookedTraining> setOfBookedTrainings;
    private String address;
    private String postCode;
    private String city;
}
