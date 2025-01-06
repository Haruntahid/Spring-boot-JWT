package com.v1.Jwt.services;

import com.v1.Jwt.model.UserPrincipal;
import com.v1.Jwt.model.Users;
import com.v1.Jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServices implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
            return new UserPrincipal(user);
    }
}
