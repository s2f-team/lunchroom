package team.s2f.lunchroom;

import team.s2f.lunchroom.model.Vote;

import java.time.LocalDateTime;

public class VoteTestData {

    public static final Vote vote_user1 = new Vote(100022, LocalDateTime.now(), UserTestData.USER_ID1, 100003, 100008);
    public static final Vote vote_user2 = new Vote(100022, LocalDateTime.now(), UserTestData.USER_ID2, 100006, 100011);
    public static final Vote vote_admin = new Vote(100022, LocalDateTime.now(), UserTestData.ADMIN_ID, 100006, 100011);

}
