package dansarkitechnology.sialicensebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingSessionRequest {

    private String centerEmailAddress;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal trainingFee;
    private int numberOfSlots;
    private String trainingType;
}
