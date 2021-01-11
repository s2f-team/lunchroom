package team.s2f.lunchroom.repository;

import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface VoteRepository {
    Vote save(Vote vote);

    boolean delete(int id, int userId);

    Vote getByUserForToday(int userId, LocalDateTime startOfDay);

    List<Vote> getAll();

}
