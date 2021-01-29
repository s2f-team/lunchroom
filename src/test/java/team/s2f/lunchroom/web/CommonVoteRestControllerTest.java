package team.s2f.lunchroom.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import team.s2f.lunchroom.RestaurantTestData;
import team.s2f.lunchroom.TestUtil;
import team.s2f.lunchroom.UserTestData;
import team.s2f.lunchroom.VoteTestData;
import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.service.VoteService;
import team.s2f.lunchroom.util.VoteUtil;
import team.s2f.lunchroom.util.exception.ErrorType;
import team.s2f.lunchroom.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommonVoteRestControllerTest extends AbstractControllerTest {
    private static final String COMMON_VOTE_REST_URL = "/rest/restaurants/votes/";

    @Autowired
    private VoteService voteService;

    @Test
    void create() throws Exception {
        voteService.delete(VoteTestData.vote_admin.getId(), UserTestData.ADMIN_ID);
        VoteTo voteTo = VoteTestData.getNew();

        ResultActions action = perform(MockMvcRequestBuilders.post(COMMON_VOTE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(voteTo))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().is(201));

        Vote created = TestUtil.readFromJson(action, Vote.class);
        int newId = created.id();

        Vote newVote = VoteUtil.createNewFromTo(voteTo, UserTestData.ADMIN_ID);
        newVote.setId(newId);

        VoteTestData.VOTE_MATCHER.assertMatch(created, newVote);
        VoteTestData.VOTE_MATCHER.assertMatch(voteService.get(newId, UserTestData.ADMIN_ID), newVote);
    }

    @Test
    void createInvalid() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.post(COMMON_VOTE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(""))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().is(422))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    void createIncorrect() throws Exception {
        VoteTo voteTo = new VoteTo(RestaurantTestData.NOT_FOUND);

        ResultActions action = perform(MockMvcRequestBuilders.post(COMMON_VOTE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(voteTo))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().is(422))
                .andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }

    @Test
    void updateInvalid() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.post(COMMON_VOTE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(""))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().is(422))
                .andExpect(errorType(ErrorType.VALIDATION_ERROR));
    }

    @Test
    void updateIncorrect() throws Exception {
        VoteTo voteTo = new VoteTo(RestaurantTestData.NOT_FOUND);

        ResultActions action = perform(MockMvcRequestBuilders.put(COMMON_VOTE_REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(voteTo))
                .with(TestUtil.userHttpBasic(UserTestData.admin)))
                .andDo(print())
                .andExpect(status().is(422))
                .andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }
}