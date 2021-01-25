package team.s2f.lunchroom.repository;

import team.s2f.lunchroom.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote);

    boolean delete(int id, int userId);

    Vote get(int id, int userId);

    Vote getByUserForToday(int userId, LocalDate today);

    List<Vote> getAll();
}