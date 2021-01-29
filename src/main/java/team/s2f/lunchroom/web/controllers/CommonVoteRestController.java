package team.s2f.lunchroom.web.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.service.VoteService;
import team.s2f.lunchroom.web.SecurityUtil;

import java.net.URI;

import static org.slf4j.LoggerFactory.getLogger;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rest/restaurants/votes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonVoteRestController {
    private static final Logger log = getLogger(CommonVoteRestController.class);

    private final VoteService voteService;

    //Create new vote
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> create(@RequestBody VoteTo voteTo) {
        int userId = SecurityUtil.authUserId();
        Vote created = voteService.createOrUpdate(voteTo, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/restaurants/votes/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    //Update vote
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody VoteTo voteTo) {
        int userId = SecurityUtil.authUserId();
        voteService.createOrUpdate(voteTo, userId);
    }
}