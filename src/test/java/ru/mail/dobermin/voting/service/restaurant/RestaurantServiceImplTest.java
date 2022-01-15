package ru.mail.dobermin.voting.service.restaurant;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mail.dobermin.voting.ControllerTest;
import ru.mail.dobermin.voting.entity.Restaurant;
import ru.mail.dobermin.voting.repository.RestaurantRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestaurantServiceImplTest extends ControllerTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Test
    void getAll() {
        when(restaurantRepository.findAll()).thenReturn(getRestaurants());
        List<Restaurant> result = getRestaurantService().getAll();
        assertEquals(getMenus().size(), result.size());
    }

    @Test
    void getById() {
        Long id = getId();
        Restaurant newRestaurant = getRestaurants().get(0);
        newRestaurant.setId(id);
        when(restaurantRepository.getById(id)).thenReturn(newRestaurant);
        Restaurant result = getRestaurantService().getById(String.valueOf(id));
        assertEquals(id, result.getId());
    }

    @Test
    void update() {
        Long id = getId();
        Restaurant newRestaurant = getRestaurants().get(0);
        newRestaurant.setId(id);
        when(restaurantRepository.findById(id)).thenReturn(java.util.Optional.of(newRestaurant));
        when(restaurantRepository.save(newRestaurant)).thenReturn(newRestaurant);
        Restaurant result = getRestaurantService().update(String.valueOf(getId()), newRestaurant);
        assertEquals(newRestaurant.getId(), result.getId());
    }

    @Test
    void saveAll() {
        List<Restaurant> restaurants = getRestaurants().stream().toList();
        when(restaurantRepository.saveAll(restaurants)).thenReturn(restaurants);
        List<Restaurant> result = getRestaurantService().saveAll(restaurants);
        assertEquals(restaurants.size(), result.size());
    }

    @Test
    void delete() {
        Long id = getId();
        getRestaurantService().delete(String.valueOf(id));
        verify(restaurantRepository).deleteById(id);
    }
}