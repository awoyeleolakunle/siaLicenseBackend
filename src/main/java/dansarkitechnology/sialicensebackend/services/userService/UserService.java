package dansarkitechnology.sialicensebackend.services.userService;


import dansarkitechnology.sialicensebackend.data.models.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    User findUserByEmailAddress(String emailAddress);

}
