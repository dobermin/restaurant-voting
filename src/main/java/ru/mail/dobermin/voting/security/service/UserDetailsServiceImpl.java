package ru.mail.dobermin.voting.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.security.repository.UserSecurityRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserSecurityRepository userRepository;
    private final String message = "Пользователь не найден с именем: ";

    public UserDetailsServiceImpl(UserSecurityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(message + username));
        return UserDetailsImpl.build(user);
    }

}
