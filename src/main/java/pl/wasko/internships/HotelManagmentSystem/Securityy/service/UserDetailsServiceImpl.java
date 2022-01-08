package pl.wasko.internships.HotelManagmentSystem.Securityy.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.Role;
import pl.wasko.internships.HotelManagmentSystem.Securityy.model.User;
import pl.wasko.internships.HotelManagmentSystem.Securityy.repository.UserRepositoryy;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepositoryy userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));


        return new org.springframework.security
                .core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getEnabled(), true, true,
                true, getAuthorities("USER"));

    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }

//    public Collection<? extends GrantedAuthority> getAuthorities(Role role) {
//
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for(Role role:Role.values()) {
//            authorities.add(new SimpleGrantedAuthority(role.name()));
//        }
//
//        return authorities;
//    }
}
