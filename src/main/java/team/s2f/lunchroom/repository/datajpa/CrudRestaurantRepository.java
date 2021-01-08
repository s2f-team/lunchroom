package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Restaurant getByName(String name);

    Restaurant getById(Integer id);

    //  @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
   /* @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes")
    List<Restaurant> getAllWithDishes();*/

   /* @Query("SELECT r FROM Restaurant r JOIN r.menu JOIN FETCH r.menu.dishes WHERE r.menu.date=?1")
    List<Restaurant> getActualWithMenu(LocalDate date);*/

}
