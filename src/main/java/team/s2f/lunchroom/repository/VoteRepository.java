package team.s2f.lunchroom.repository;

import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Vote;


public interface VoteRepository {
    Vote save(Vote vote);

    boolean delete(int id, int userId);

}
