package ru.mail.dobermin.voting.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mail.dobermin.voting.entity.Menu;
import ru.mail.dobermin.voting.service.menu.MenuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Admin/Menus")
public class AdminMenuController extends AdminController {

    private final MenuService menuService;

    @PostMapping("/menu")
    @Operation(summary = "Adds new menus")
    public ResponseEntity<?> create(@RequestBody List<Menu> menus) {
        try {
            return ResponseEntity.ok().body(menuService.saveAll(menus));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/menu/{id}")
    @Operation(summary = "Changes menu by id")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Menu menu) {
        try {
            return ResponseEntity.ok().body(menuService.update(id, menu));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/menu/{id}")
    @Operation(summary = "Deletes menu by id")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            menuService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
