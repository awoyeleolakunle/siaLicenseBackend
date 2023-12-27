package dansarkitechnology.sialicensebackend.data;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userEmailAddress;
    private String jwt;
    private boolean isRevoked;
    private boolean isExpired;
}
