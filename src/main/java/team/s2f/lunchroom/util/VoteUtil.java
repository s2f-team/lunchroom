package team.s2f.lunchroom.util;

import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;

import java.time.LocalDateTime;

public class VoteUtil {
    public static Vote createNewFromTo(VoteTo voteTo, int userId) {
        return new Vote(null, LocalDateTime.now(), userId, voteTo.getRestaurantId(), voteTo.getMenuId());
    }
}
