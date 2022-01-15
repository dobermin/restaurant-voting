package ru.mail.dobermin.voting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.dobermin.voting.service.menu.MenuService;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Tag(name = "Menus")
public final class MenuController {

    private final MenuService menuService;

    @GetMapping("")
    @Operation(summary = "Returns all menus")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok().body(menuService.getAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a menu by id")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(menuService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
