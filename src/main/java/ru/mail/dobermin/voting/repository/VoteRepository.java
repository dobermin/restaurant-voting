package ru.mail.dobermin.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.entity.Vote;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findFirstByUserAndVotedDate(User user, String date);
}
