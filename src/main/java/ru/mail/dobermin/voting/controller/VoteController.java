package ru.mail.dobermin.voting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.dobermin.voting.entity.Restaurant;
import ru.mail.dobermin.voting.entity.User;
import ru.mail.dobermin.voting.service.restaurant.RestaurantService;
import ru.mail.dobermin.voting.service.user.UserService;
import ru.mail.dobermin.voting.service.vote.VoteService;

import java.security.Principal;

@RestController
@RequestMapping("/api/vote")
@RequiredArgsConstructor
@Tag(name = "Votes")
public final class VoteController {

    private final VoteService voteService;
    private final RestaurantService restaurantService;
    private final UserService userService;

    @PutMapping("/restaurant/{id}")
    @Operation(summary = "Votes for restaurant")
    public ResponseEntity<?> votes(Principal principal, @PathVariable String id) {
        try {
            Restaurant restaurant = restaurantService.getById(id);
            User user = userService.getByLogin(principal.getName());
            voteService.save(user, restaurant);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
