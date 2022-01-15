package ru.mail.dobermin.voting.service.dish;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mail.dobermin.voting.ControllerTest;
import ru.mail.dobermin.voting.entity.Dish;
import ru.mail.dobermin.voting.repository.DishRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DishServiceImplTest extends ControllerTest {

    @Mock
    private DishRepository dishRepository;

    @Test
    void getAll() {
        when(dishRepository.findAll()).thenReturn(getDishes());
        List<Dish> result = getDishService().getAll();
        assertEquals(getDishes().size(), result.size());
    }

    @Test
    void getById() {
        Long id = getId();
        Dish newDish = getDishes().get(0);
        newDish.setId(id);
        when(dishRepository.getById(id)).thenReturn(newDish);
        Dish result = getDishService().getById(String.valueOf(id));
        assertEquals(id, result.getId());
    }

    @Test
    void update() {
        Long id = getId();
        Dish newDish = getDishes().get(0);
        newDish.setId(id);
        when(dishRepository.findById(id)).thenReturn(java.util.Optional.of(newDish));
        when(dishRepository.save(newDish)).thenReturn(newDish);
        Dish result = getDishService().update(String.valueOf(getId()), newDish);
        assertEquals(newDish.getId(), result.getId());
    }

    @Test
    void saveAll() {
        List<Dish> dishes = getDishes().stream().toList();
        when(dishRepository.saveAll(dishes)).thenReturn(dishes);
        List<Dish> result = getDishService().saveAll(dishes);
        assertEquals(dishes.size(), result.size());
    }

    @Test
    void delete() {
        Long id = getId();
        getDishService().delete(String.valueOf(id));
        verify(dishRepository).deleteById(id);
    }
}