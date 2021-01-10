package team.s2f.lunchroom.web.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.service.VoteService;
import team.s2f.lunchroom.util.ValidationUtil;
import team.s2f.lunchroom.web.SecurityUtil;

import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("rest/restaurants/votes")
public class VoteRestController {
    private static final Logger log = getLogger(VoteRestController.class);

    private final VoteService voteService;

    @Autowired
    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote create(@RequestBody Vote vote) {
        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkNew(vote);
        vote.setUserId(userId);
        vote.setDateTime(LocalDateTime.now());
        log.info("New vote {} by user {}.", vote, userId);
        return voteService.create(vote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        voteService.delete(id, userId);
    }
}
