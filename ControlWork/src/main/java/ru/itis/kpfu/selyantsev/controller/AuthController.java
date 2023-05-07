package ru.itis.kpfu.selyantsev.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.kpfu.selyantsev.aspect.annotation.CollectData;
import ru.itis.kpfu.selyantsev.dto.JwtRequest;
import ru.itis.kpfu.selyantsev.dto.JwtResponse;
import ru.itis.kpfu.selyantsev.dto.RefreshJwtRequest;
import ru.itis.kpfu.selyantsev.model.User;
import ru.itis.kpfu.selyantsev.service.AuthService;
import ru.itis.kpfu.selyantsev.service.UserService;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @CollectData
    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @CollectData
    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @CollectData
    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @CollectData
    @PostMapping("register")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

}