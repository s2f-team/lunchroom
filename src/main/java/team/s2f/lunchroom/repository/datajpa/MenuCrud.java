package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuCrud extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Menu m where m.id=:id")
    int delete(@Param("id") int id);

    @Query("select m from Menu m where m.restaurant.id=:id and m.date=:date")
    Menu getByRestaurantId(@Param("id") int restaurantId, @Param("date")LocalDate date);

    //  @EntityGraph(attributePaths = {"restaurants"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Menu m join fetch m.restaurant where m.date=:date")
    List<Menu> getAllWithRestaurant(@Param("date") LocalDate date);
}
