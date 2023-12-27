package dansarkitechnology.sialicensebackend.dtos.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApplicantRequest {
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String postCode;
    private String address;
    private String city;
}
