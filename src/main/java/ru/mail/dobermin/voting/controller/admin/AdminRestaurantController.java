package ru.mail.dobermin.voting.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mail.dobermin.voting.entity.Restaurant;
import ru.mail.dobermin.voting.service.restaurant.RestaurantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Admin/Restaurants")
public class AdminRestaurantController extends AdminController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant")
    @Operation(summary = "Adds new restaurants")
    public ResponseEntity<?> create(@RequestBody List<Restaurant> restaurants) {
        try {
            return ResponseEntity.ok().body(restaurantService.saveAll(restaurants));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/restaurant/{id}")
    @Operation(summary = "Changes restaurant by id")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity.ok().body(restaurantService.update(id, restaurant));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/restaurant/{id}")
    @Operation(summary = "Deletes restaurant by id")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            restaurantService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
