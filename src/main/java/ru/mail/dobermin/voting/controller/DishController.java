package ru.mail.dobermin.voting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.dobermin.voting.service.dish.DishService;

@RestController
@RequestMapping("/api/dish")
@RequiredArgsConstructor
@Tag(name = "Dishes")
public final class DishController {

    private final DishService dishService;

    @GetMapping("")
    @Operation(summary = "Returns all dishes")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok().body(dishService.getAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a dish by id")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(dishService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
