package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Dish;
import team.s2f.lunchroom.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteCrud extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("delete from Vote v where v.id=:id and v.userId=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("select v from Vote v where v.id=:id and v.userId=:userId")
    Optional<Vote> findByIdAndUserId(@Param("id") int id, @Param("userId") int userId);

    @Query("select v from Vote v where v.userId=:userId and v.dateTime>:date")
    Optional<Vote> getByUserForToday(@Param("userId") int userId, @Param("date") LocalDateTime startOfDay);
}
