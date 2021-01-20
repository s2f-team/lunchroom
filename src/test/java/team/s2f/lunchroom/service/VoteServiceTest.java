package team.s2f.lunchroom.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import team.s2f.lunchroom.UserTestData;
import team.s2f.lunchroom.VoteTestData;
import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.util.VoteUtil;
import team.s2f.lunchroom.util.exception.ApplicationException;
import team.s2f.lunchroom.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService voteService;

    @Test
    void create() {
        voteService.delete(VoteTestData.VOTE_USER1_ID, UserTestData.USER_ID1);
        Vote created = voteService.createOrUpdate(VoteTestData.getNew(), UserTestData.USER_ID1);
        int newId = created.id();
        Vote newVote = VoteUtil.createNewFromTo(VoteTestData.getNew(), UserTestData.USER_ID1);
        newVote.setId(newId);
        VoteTestData.VOTE_MATCHER.assertMatch(created, newVote);
        VoteTestData.VOTE_MATCHER.assertMatch(voteService.get(newId, UserTestData.USER_ID1), newVote);
    }

    @Test
    void updateBefore11Am() {
        VoteTo upd = VoteTestData.getUpdated();
        Vote updated = voteService.createOrUpdateJustForTest(upd, UserTestData.USER_ID1, LocalTime.of(9, 0));
        VoteTestData.VOTE_MATCHER.assertMatch(voteService.get(100022, UserTestData.USER_ID1), updated);
    }

    @Test
    void updateAfter11Am() {
        VoteTo updated = VoteTestData.getUpdated();
        Assertions.assertThrows(ApplicationException.class, () -> voteService.createOrUpdateJustForTest(updated, UserTestData.USER_ID1, LocalTime.of(11,0)));
    }

    @Test
    void delete() {
        voteService.delete(100022, UserTestData.USER_ID1);
        Assertions.assertThrows(NotFoundException.class, () -> voteService.get(100022, UserTestData.USER_ID1));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> voteService.delete(100022, UserTestData.NOT_FOUND));
    }
}