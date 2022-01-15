package ru.mail.dobermin.voting.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.mail.dobermin.voting.util.DateTimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_time")
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<Dish> dishes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
//    @JsonIgnore
    private Restaurant restaurant;

    public Menu(LocalDateTime dateTime, List<Dish> dishes, Restaurant restaurant) {
        this.dateTime = dateTime;
        this.dishes = dishes;
        this.restaurant = restaurant;
    }
}
