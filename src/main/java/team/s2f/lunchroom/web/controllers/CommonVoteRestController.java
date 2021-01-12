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

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    public Vote createOrUpdate(@RequestBody Vote vote) {
        int userId = SecurityUtil.authUserId();
        LocalDateTime today = LocalDate.now().atStartOfDay();

        LocalDateTime now = LocalDateTime.now();
        Vote existing = voteService.getByUserForToday(userId, today);
        if (existing != null) {
            if (now.getHour() < 11) {
                existing.setDateTime(LocalDateTime.now());
                existing.setMenuId(vote.getMenuId());
                existing.setRestaurantId(vote.getRestaurantId());
                log.info("Change vote {} by user {} for today.", vote, userId);
                return voteService.createOrUpdate(existing);
            } else {
                return null;
            }
        }
        ValidationUtil.checkNew(vote);
        vote.setUserId(userId);
        vote.setDateTime(LocalDateTime.now());
        log.info("New vote {} by user {}.", vote, userId);
        return voteService.createOrUpdate(vote);
    }

    //Delete vote
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        voteService.delete(id, userId);
    }

}
