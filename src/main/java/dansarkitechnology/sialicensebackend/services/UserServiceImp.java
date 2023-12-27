package dansarkitechnology.sialicensebackend.services;

import dansarkitechnology.sialicensebackend.data.models.User;
import dansarkitechnology.sialicensebackend.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddressIgnoreCase(emailAddress).orElse(null);
    }

}
