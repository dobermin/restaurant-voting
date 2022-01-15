package ru.mail.dobermin.voting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.dobermin.voting.service.restaurant.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
@Tag(name = "Restaurants")
public final class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("")
    @Operation(summary = "Returns all restaurants")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok().body(restaurantService.getAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a restaurant by id")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(restaurantService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
