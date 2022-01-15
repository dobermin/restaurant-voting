package ru.mail.dobermin.voting.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.repository.UserRepository;

import java.util.List;

import static ru.mail.dobermin.voting.security.enums.Role.ADMIN;
import static ru.mail.dobermin.voting.security.enums.Role.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public User update(String id, User user) {
        return null;
    }

    @Override
    public List<User> saveAll(List<User> t) {
        return null;
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public void registration(User user) throws Exception {
        User u = userRepository.findByLogin(user.getLogin()).orElse(null);
        if (u != null) throw new Exception();
        user.setRole(USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        long count = userRepository.count();
        if (count == 0) user.setRole(ADMIN);

        userRepository.save(user);
    }

    @Override
    public User authorization(User user) {
        try {
            user = userRepository.findByLogin(user.getLogin()).orElseThrow();
        } catch (Exception e) {
            return null;
        }

        return user;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow();
    }
}
