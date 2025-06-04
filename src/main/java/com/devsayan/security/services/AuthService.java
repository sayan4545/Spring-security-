package com.devsayan.security.services;

import com.devsayan.security.dtos.LoginDTO;
import com.devsayan.security.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public String login(LoginDTO loginDTO){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),loginDTO.getPassword()
        ));

        User user = (User)authenticate.getPrincipal();
        return jwtService.generateToken(user);
    }
}
