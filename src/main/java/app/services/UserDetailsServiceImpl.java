package app.services;

import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

import static java.util.Collections.emptyList;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private static final Set<GrantedAuthority> DEFAULT_AUTHORITY = Collections.singleton(new SimpleGrantedAuthority("USER"));

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        app.models.User applicationUser = userRepository.findByEmail(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getEmail(), applicationUser.getPassword(), emptyList());
    }

    /*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return User.withUsername(email)
                .password(userRepository.findByEmail(email).getPassword())
                .authorities(new ArrayList<>(DEFAULT_AUTHORITY)).build();
    }
     */
}