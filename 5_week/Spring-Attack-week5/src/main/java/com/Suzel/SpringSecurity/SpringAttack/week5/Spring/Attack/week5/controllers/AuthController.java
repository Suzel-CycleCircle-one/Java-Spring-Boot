package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.controllers;


import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.LogInDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.LoginResponseDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.SignUpDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.dto.UserDTO;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services.AuthService;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private  String deployEnv;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public  ResponseEntity<LoginResponseDTO> login(@RequestBody LogInDTO logInDTO , HttpServletRequest request
    , HttpServletResponse response){
        LoginResponseDTO loginResponseDTO =  authService.login(logInDTO);

        Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDTO);
    }
    @PostMapping("/refresh")
    public  ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("" +
                        "Refresh Token Not Found inside the Cookies"));

        LoginResponseDTO loginResponseDTO = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);
    }

}
