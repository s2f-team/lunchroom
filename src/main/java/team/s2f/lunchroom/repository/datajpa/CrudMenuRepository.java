package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    //  @EntityGraph(attributePaths = {"restaurants"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("from Menu m join fetch m.restaurant")
    List<Menu> getAllWithRestaurant();


    /* List<Menu> getMenuWithRestaurantIdForToday(LocalDate date);*/
}
