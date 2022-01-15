package ru.mail.dobermin.voting.service.vote;

import ru.mail.dobermin.voting.entity.Restaurant;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.entity.Vote;
import ru.mail.dobermin.voting.service.Service;

public interface VoteService extends Service<Vote> {

    void save(User user, Restaurant restaurant);
}
