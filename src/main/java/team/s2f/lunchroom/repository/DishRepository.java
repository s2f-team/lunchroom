package team.s2f.lunchroom.repository;

import team.s2f.lunchroom.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int menuId);

    Dish get(int id, int menuId);

    Dish getByNameForMenu(String name, int menuId);

    boolean delete(int id, int menuId);

    List<Dish> getAllByMenu(int menuId);
}
