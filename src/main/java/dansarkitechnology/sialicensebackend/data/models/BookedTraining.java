package dansarkitechnology.sialicensebackend.data.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookedTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Center center;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TrainingSession trainingSession;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Applicant applicant;

}
