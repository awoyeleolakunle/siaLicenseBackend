package dansarkitechnology.sialicensebackend.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequest {
    @NotBlank
    @NotEmpty
    private String emailAddress;
    @NotBlank
    @NotEmpty
    private String password;
}
