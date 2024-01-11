package dansarkitechnology.sialicensebackend.data.models;


import dansarkitechnology.sialicensebackend.data.enums.TrainingType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int NUmberOfSlots;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal trainingFee;
    @Enumerated(EnumType.STRING)
    private TrainingType trainingType;
    private String centerName;
    private String centerEmailAddress;
    private String centerCity;
    private String centerAddress;
}
