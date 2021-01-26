package team.s2f.lunchroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    Menu save(Menu menu);

    @Transactional
    @Modifying
    @Query("delete from Menu m where m.id=:id")
    int delete(@Param("id") int id);

    Optional<Menu> getById(int id);

    @Query("select m from Menu m where m.restaurant.id=:id and m.date=:date")
    Menu getByRestaurantId(@Param("id") int restaurantId, @Param("date")LocalDate date);

    @Query("select m from Menu m join fetch m.restaurant where m.date=:date")
    List<Menu> getAllWithRestaurant(@Param("date") LocalDate date);
}
