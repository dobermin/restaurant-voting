package ru.mail.dobermin.voting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.mail.dobermin.voting.util.DateTimeUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "voted_date"})})
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @Column(name = "voted")
    private Date voted;

    @Column(name = "voted_date")
    @JsonIgnore
    private String votedDate;

    private void setVotedDate(String votedDate) {
        this.votedDate = "";
    }

    public void setVoted(Date voted) {
        this.voted = voted;
        this.votedDate = DateTimeUtil.dateToString(voted, DateTimeUtil.DATE_PATTERN);
    }
}
