package dansarkitechnology.sialicensebackend.dtos.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingSessionRequest {

    @NotBlank
    @NotEmpty
    private String centerEmailAddress;

   // @JsonFormat(pattern = "yyyy-MM-dd")

   // 'T'HH:mm:ss
    @NotNull
    private LocalDate startDate;

    // @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate endDate;

    private BigDecimal trainingFee;
   @Positive
    private int numberOfSlots;
    @NotBlank
    @NotEmpty
    private String trainingType;
}
