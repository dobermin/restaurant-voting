package ru.mail.dobermin.voting.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mail.dobermin.voting.entity.User;

import java.util.Optional;

@Repository
public interface UserSecurityRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
