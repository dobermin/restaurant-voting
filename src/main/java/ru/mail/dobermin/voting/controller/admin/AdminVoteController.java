package ru.mail.dobermin.voting.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.dobermin.voting.service.vote.VoteService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Admin/Votes")
public class AdminVoteController extends AdminController {

    private final VoteService voteService;

    @GetMapping("/vote")
    @Operation(summary = "Returns all votes")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok().body(voteService.getAll());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
