package ru.mail.dobermin.voting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.security.JwtTokenProvider;
import ru.mail.dobermin.voting.service.user.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Users")
public final class Controller {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/registration")
    @Operation(summary = "Registrations a new user")
    public ResponseEntity<?> registration(@Valid @RequestBody User user) {
        try {
            userService.registration(user);
            String token = jwtTokenProvider.createToken(user.getLogin(), user.getRole().name());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authorization")
    @Operation(summary = "Authorizations a user")
    public ResponseEntity<?> authorization(@RequestBody User user) {
        try {
            user = userService.authorization(user);
            String token = jwtTokenProvider.createToken(user.getLogin(), user.getRole().name());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
