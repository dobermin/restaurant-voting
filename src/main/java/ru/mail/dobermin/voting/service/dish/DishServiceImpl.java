package ru.mail.dobermin.voting.service.dish;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mail.dobermin.voting.entity.Dish;
import ru.mail.dobermin.voting.repository.DishRepository;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public Dish getById(String id) {
        return dishRepository.getById(Long.valueOf(id));
    }

    @Override
    public Dish update(String id, Dish dish) {
        Dish dishOptional = dishRepository.findById(Long.valueOf(id)).orElseThrow();
        if (Objects.equals(dishOptional.getId(), dish.getId())) {
            dishOptional.setName(dish.getName());
            dishOptional.setPrice(dish.getPrice());
            dishRepository.save(dishOptional);
            return dishOptional;
        }
        throw new EmptyStackException();
    }

    @Override
    public List<Dish> saveAll(List<Dish> dishes) {
        return dishRepository.saveAll(dishes);
    }

    @Override
    public void delete(String id) {
        dishRepository.deleteById(Long.valueOf(id));
    }

}
