package ru.mail.dobermin.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mail.dobermin.voting.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
