package ru.mail.dobermin.voting.service.restaurant;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mail.dobermin.voting.entity.Restaurant;
import ru.mail.dobermin.voting.repository.RestaurantRepository;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getById(String id) {
        return restaurantRepository.getById(Long.valueOf(id));
    }

    @Override
    public Restaurant update(String id, Restaurant restaurant) {
        Restaurant restaurantOptional = restaurantRepository.findById(Long.valueOf(id)).orElseThrow();
        if (Objects.equals(restaurantOptional.getId(), restaurant.getId())) {
            restaurantOptional.setName(restaurant.getName());
            restaurantOptional.setMenus(restaurant.getMenus());
            restaurantRepository.save(restaurantOptional);
        }
        return restaurantOptional;
    }

    @Override
    public List<Restaurant> saveAll(List<Restaurant> restaurants) {
        return restaurantRepository.saveAll(restaurants);
    }

    @Override
    public void delete(String id) {
        restaurantRepository.deleteById(Long.valueOf(id));
    }
}
