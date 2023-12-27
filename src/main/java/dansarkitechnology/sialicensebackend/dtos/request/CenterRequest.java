package dansarkitechnology.sialicensebackend.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CenterRequest {
    private String centerName;
    private String emailAddress;
    private String password;
    private String address;
    private String city;
    private String postCode;
}
