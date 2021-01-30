package team.s2f.lunchroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Dish;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Dish d where d.id=:id and d.menu.id=:menuId")
    int delete(@Param("id") int id, @Param("menuId") int menuId);

    @Query("select d from Dish d where d.id=:id and d.menu.id=:menuId")
    Optional<Dish> findById(@Param("id") int id, @Param("menuId") int menuId);

    @Query("select d from Dish d where d.name=:name and d.menu.id=:id")
    Dish getByNameForMenu(@Param("name") String name, @Param("id") int menuId);

    @Query("select d from Dish d where d.menu.id=:menuId order by d.name")
    List<Dish> getAll(@Param("menuId") int menuId);
}