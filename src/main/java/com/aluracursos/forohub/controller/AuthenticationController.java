package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.user.AuthenticateUserRequest;
import com.aluracursos.forohub.infra.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticateUserRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

