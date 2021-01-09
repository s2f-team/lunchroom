package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.s2f.lunchroom.model.Menu;

import java.time.LocalDate;
import java.util.List;


public interface MenuRepository {
    List<Menu> getAll();

    List<Menu>     getAllWithRestaurant();

}
