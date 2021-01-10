package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface DishCrud extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Dish d where d.id=:id and d.menu.id=:menuId")
    int delete(@Param("id") int id, @Param("menuId") int menuId);

    @Query("select d from Dish d where d.id=:id and d.menu.id=:menuId")
    Dish findById(@Param("id") int id, @Param("menuId") int menuId);

    @Query("select d from Dish d where d.menu.id=:menuId order by d.id")
    List<Dish> getAll(@Param("menuId") int menuId);
}
