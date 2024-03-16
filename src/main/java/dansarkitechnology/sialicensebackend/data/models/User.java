package dansarkitechnology.sialicensebackend.data.models;


import dansarkitechnology.sialicensebackend.data.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emailAddress;
    private String password;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    private Set<Roles> roles;
    private LocalDateTime registrationDate;
    private String firstName;
    private String lastName;
    private String middleName;
}
