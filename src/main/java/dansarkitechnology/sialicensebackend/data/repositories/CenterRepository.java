package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.models.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Long> {

    Center findByUser_EmailAddress(String emailAddress);
}
