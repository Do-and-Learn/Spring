package idv.teddy.service;

import idv.teddy.domain.User;
import idv.teddy.repository.UserDetailsRepository;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Data
@Component
public class JpaUserDetailsService implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDetailsRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Cannot find user [" + username + "]");
        }
        return user;
    }
}
