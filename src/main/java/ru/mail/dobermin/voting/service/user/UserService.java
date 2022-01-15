package ru.mail.dobermin.voting.service.user;

import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.service.Service;

public interface UserService extends Service<User> {

    void registration(User user) throws Exception;

    User authorization(User user);

    User getByLogin(String login);
}
