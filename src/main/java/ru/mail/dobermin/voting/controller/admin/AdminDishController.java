package ru.mail.dobermin.voting.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mail.dobermin.voting.entity.Dish;
import ru.mail.dobermin.voting.service.dish.DishService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Admin/Dishes")
public class AdminDishController extends AdminController {

    private final DishService dishService;

    @GetMapping("/dish")
    @Operation(summary = "Returns all dishes")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok().body(dishService.getAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dish")
    @Operation(summary = "Adds new dishes")
    public ResponseEntity<?> create(@RequestBody List<Dish> dishes) {
        try {
            return ResponseEntity.ok().body(dishService.saveAll(dishes));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/dish/{id}")
    @Operation(summary = "Changes dish by id")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Dish dish) {
        try {
            return ResponseEntity.ok().body(dishService.update(id, dish));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/dish/{id}")
    @Operation(summary = "Deletes dish by id")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            dishService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
