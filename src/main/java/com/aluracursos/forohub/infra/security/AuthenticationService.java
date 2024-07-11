package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.user.AuthenticateUserRequest;
import com.aluracursos.forohub.domain.user.User;
import com.aluracursos.forohub.domain.user.UserRepository;
import com.aluracursos.forohub.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(AuthenticateUserRequest request) {
        User user = (User) userRepository.findByEmail(request.getEmail());
        if (user.getPassword().equals(request.getPassword())) {
            return user;
        }
        throw new BadCredentialsException("Incorrect credentials");
    }
}
