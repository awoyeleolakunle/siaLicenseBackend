package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Applicant findByUser_EmailAddress(String emailAddress);

}
