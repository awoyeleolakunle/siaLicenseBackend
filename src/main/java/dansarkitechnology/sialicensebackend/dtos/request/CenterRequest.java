package dansarkitechnology.sialicensebackend.dtos.request;


import jakarta.validation.constraints.Email;
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

public class CenterRequest {
    @NotBlank
    @NotEmpty
    private String centerName;
    @NotBlank
    @Email
    private String emailAddress;
    @NotBlank
    @NotEmpty
    private String password;
    @NotBlank
    @NotEmpty
    private String address;
    @NotBlank
    @NotEmpty
    private String city;
    @NotBlank
    @NotEmpty
    private String postCode;
}
