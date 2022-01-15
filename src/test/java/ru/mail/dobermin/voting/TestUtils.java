package ru.mail.dobermin.voting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.security.JwtTokenProvider;
import ru.mail.dobermin.voting.service.user.UserService;

@Service
@RequiredArgsConstructor
public class TestUtils {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public String getToken(User user) {
        user = userService.authorization(user);
        return jwtTokenProvider.createToken(user.getLogin(), user.getRole().name());
    }
}
