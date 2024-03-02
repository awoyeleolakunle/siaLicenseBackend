package dansarkitechnology.sialicensebackend.services.userService;

import dansarkitechnology.sialicensebackend.data.models.User;
import dansarkitechnology.sialicensebackend.data.repositories.UserRepository;
import dansarkitechnology.sialicensebackend.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

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
