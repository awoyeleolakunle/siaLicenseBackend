package dansarkitechnology.sialicensebackend.dtos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApplicantRequest {

    @NotBlank
    @Email(message = "Email should be valid")
    private String emailAddress;
    @NotBlank
    @NotEmpty(message = "Password should be valid ")
    private String password;
    @NotBlank
    @NotEmpty(message = "   First name should not be empty")
    private String firstName;
    @NotBlank
    @NotEmpty(message = "Last Name should not be empty")
    private String lastName;
    @NotBlank
    @NotEmpty(message = "Post Code should not be empty")
    private String postCode;
    @NotBlank
    @NotEmpty(message = "Address should not be empty")
    private String address;
    @NotBlank
    @NotEmpty(message = "city should not be empty")
    private String city;
}
