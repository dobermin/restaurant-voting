package ru.mail.dobermin.voting.service.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mail.dobermin.voting.entity.Restaurant;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.entity.Vote;
import ru.mail.dobermin.voting.repository.VoteRepository;
import ru.mail.dobermin.voting.util.DateTimeUtil;

import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    @Value("${app.canVote}")
    private String canVote;

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @Override
    public Vote getById(String id) {
        return voteRepository.getById(Long.valueOf(id));
    }

    @Override
    public Vote update(String id, Vote vote) {
        return null;
    }

    @Override
    public List<Vote> saveAll(List<Vote> t) {
        return null;
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public void save(User user, Restaurant restaurant) {
        Date date = new Date();
        if (!DateTimeUtil.canVote(canVote, date)) throw new EmptyStackException();
        Vote vote = voteRepository.findFirstByUserAndVotedDate(
                user,
                DateTimeUtil.dateToString(date, DateTimeUtil.DATE_PATTERN)
        ).orElse(new Vote());
        vote.setUser(user);
        vote.setRestaurant(restaurant);
        vote.setVoted(date);

        voteRepository.save(vote);
    }
}
