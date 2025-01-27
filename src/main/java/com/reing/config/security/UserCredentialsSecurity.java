package com.reing.config.security;

import com.reing.model.entity.User;
import com.reing.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCredentialsSecurity implements UserDetailsService {

    private final UserRepository userRepository;

    public UserCredentialsSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userDb = userRepository.findByUsername(username).orElse(null);

        List<GrantedAuthority> grantedAuthorities = null;

        if(userDb != null) {
            grantedAuthorities = userDb.getRoles()
                    .stream()
                    .map(rol -> new SimpleGrantedAuthority(rol.getName()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    userDb.getUsername(),
                    userDb.getPassword(),
                    userDb.isEnabled(),
                    true,
                    true,
                    true,
                    grantedAuthorities);
        }

        return null;
    }
}
