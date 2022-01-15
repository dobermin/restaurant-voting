package ru.mail.dobermin.voting;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mail.dobermin.voting.entity.Dish;
import ru.mail.dobermin.voting.entity.Menu;
import ru.mail.dobermin.voting.entity.Restaurant;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.security.JwtTokenProvider;
import ru.mail.dobermin.voting.service.dish.DishServiceImpl;
import ru.mail.dobermin.voting.service.menu.MenuServiceImpl;
import ru.mail.dobermin.voting.service.restaurant.RestaurantServiceImpl;
import ru.mail.dobermin.voting.service.user.UserServiceImpl;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
abstract public class ControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    @InjectMocks
    private DishServiceImpl dishService;
    @InjectMocks
    private RestaurantServiceImpl restaurantService;
    @InjectMocks
    private MenuServiceImpl menuService;
    @InjectMocks
    private UserServiceImpl userService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    private String urlAdminDish, urlMenu;
    private List<Dish> dishes;
    private List<Menu> menus;
    private List<Restaurant> restaurants;
    private String token;
    private User user;
    private Long id = 10L;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        dishes = List.of(
                new Dish("Пицца", 15.3)
                ,
                new Dish("Суп", 3.2)
        );
        restaurants = List.of(
                new Restaurant("Western")
        );
        menus = List.of(
                new Menu(new Timestamp(new Date().getTime()).toLocalDateTime(), dishes, restaurants.get(0))
        );
        user = new User("admin", "root");
        token = jwtTokenProvider.createToken(user.getLogin(), user.getLogin());
        urlAdminDish = "/api/admin/dish";
        urlMenu = "/api/menu";
    }
}
