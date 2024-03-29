package dansarkitechnology.sialicensebackend.data.repositories;

import dansarkitechnology.sialicensebackend.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailAddressIgnoreCase(String emailAddress);
}


