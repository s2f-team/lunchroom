package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantCrud extends JpaRepository<Restaurant, Integer> {
    @Query("select r from Restaurant r where r.id=:id")
    Restaurant getById(@Param("id") int id);

    @Query("select r from Restaurant r where r.name=:name")
    Restaurant getByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from Restaurant r where r.id=:id")
    int delete(@Param("id") int id);

}
