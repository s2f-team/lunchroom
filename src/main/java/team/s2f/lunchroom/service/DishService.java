package team.s2f.lunchroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Dish;
import team.s2f.lunchroom.repository.DishRepository;
import team.s2f.lunchroom.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "Dish must not be null.");
        return dishRepository.save(dish, menuId);
    }

    public void update(Dish dish, int menuId) {
        Assert.notNull(dish, "Dish must not be null.");
        ValidationUtil.checkNotFoundWithId(dishRepository.save(dish, menuId), dish.getId());
    }

    public Dish get(int id, int menuId) {
        return ValidationUtil.checkNotFoundWithId(dishRepository.get(id, menuId), id);
    }

    public void delete(int id, int menuId) {
        ValidationUtil.checkNotFoundWithId(dishRepository.delete(id, menuId), id);
    }

    public List<Dish> getAllByMenuId(int menuId) {
        return dishRepository.getAllByMenu(menuId);
    }
}
