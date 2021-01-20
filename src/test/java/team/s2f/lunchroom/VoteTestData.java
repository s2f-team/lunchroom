package team.s2f.lunchroom;

import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;

import java.time.LocalDateTime;

public class VoteTestData {
    public static final TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingEqualsComparator(Vote.class);

    public static final int VOTE_USER1_ID = 100022;

    public static final Vote vote_user1 = new Vote(100022, LocalDateTime.now(), UserTestData.USER_ID1, 100003, 100008);
    public static final Vote vote_user2 = new Vote(100023, LocalDateTime.now(), UserTestData.USER_ID2, 100006, 100011);
    public static final Vote vote_admin = new Vote(100024, LocalDateTime.now(), UserTestData.ADMIN_ID, 100006, 100011);

    public static VoteTo getNew() {
        return new VoteTo(100003, 100008);
    }

    public static VoteTo getUpdated() {
        return new VoteTo(100006, 100011);
    }

}
