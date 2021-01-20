package team.s2f.lunchroom.web.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.service.VoteService;
import team.s2f.lunchroom.web.SecurityUtil;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("rest/restaurants/votes")
public class CommonVoteRestController {
    private static final Logger log = getLogger(CommonVoteRestController.class);

    private final VoteService voteService;

    @Autowired
    public CommonVoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    //Vote
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote vote(@RequestBody VoteTo voteTo) {
        int userId = SecurityUtil.authUserId();
        return voteService.createOrUpdate(voteTo, userId);
    }

    //Delete vote
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        voteService.delete(id, userId);
    }

}
