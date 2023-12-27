package dansarkitechnology.sialicensebackend.security;


import dansarkitechnology.sialicensebackend.data.models.User;
import dansarkitechnology.sialicensebackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecuredUserService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByEmailAddress(username);
        return new SecuredUser(user);
    }
}
