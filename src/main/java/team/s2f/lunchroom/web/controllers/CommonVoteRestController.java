package team.s2f.lunchroom.web.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.service.VoteService;
import team.s2f.lunchroom.web.SecurityUtil;

import static org.slf4j.LoggerFactory.getLogger;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "rest/restaurants/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonVoteRestController {
    private static final Logger log = getLogger(CommonVoteRestController.class);

    private final VoteService voteService;

    //Create new vote
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Vote create(@RequestBody VoteTo voteTo) {
        int userId = SecurityUtil.authUserId();
        return voteService.createOrUpdate(voteTo, userId);
    }

    //Update vote
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody VoteTo voteTo) {
        int userId = SecurityUtil.authUserId();
        voteService.createOrUpdate(voteTo, userId);
    }
}