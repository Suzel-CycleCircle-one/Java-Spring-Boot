package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services;

import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.LogInDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.LoginResponseDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final AuthenticationManager authenticationManager;
    private  final  JwtService jwtService;
    private  final  UserService userService;
    private  final  SessionService sessionService;

    public LoginResponseDTO login(LogInDTO logInDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logInDTO.getEmail(), logInDTO.getPassword()));

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        sessionService.genrateNewSession(user, refreshToken);

        return   new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDTO refreshToken(String refreshToken) {
        Long userId =  jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        UserEntity user = (UserEntity) userService.getUserById(userId);

        String accessToken = jwtService.generateAccessToken(user);
        return   new LoginResponseDTO(user.getId(), accessToken, refreshToken);

    }
}
