package com.example.spring.security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<MyUser> byUsername = myUserRepository.findByUsername(username);

        if (byUsername.isPresent()) {
            MyUser user = byUsername.get();
            return User
                    .builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(getRoles(user))
                    .build();
        }
        else
            throw new UsernameNotFoundException(username);
    }

    private String[] getRoles(MyUser user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
