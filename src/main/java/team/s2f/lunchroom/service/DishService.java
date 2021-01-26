package team.s2f.lunchroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Dish;
import team.s2f.lunchroom.repository.DishRepository;
import team.s2f.lunchroom.repository.MenuRepository;
import team.s2f.lunchroom.util.ValidationUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;

    public Dish create(Dish dish, int menuId) {
        Assert.notNull(dish, "Dish must not be null.");
        ValidationUtil.checkNameIsUniqueForMenu(dishRepository.getByNameForMenu(dish.getName(), menuId) != null);
        dish.setMenu(menuRepository.getOne(menuId));
        return save(dish, menuId);
    }

    public void update(Dish dish, int menuId) {
        Assert.notNull(dish, "Dish must not be null.");
        ValidationUtil.checkNotFoundWithId(save(dish, menuId), dish.getId());
    }

    public Dish get(int id, int menuId) {
        return ValidationUtil.checkNotFoundWithId(dishRepository.findById(id, menuId), id);
    }

    public void delete(int id, int menuId) {
        ValidationUtil.checkSingleModification(dishRepository.delete(id, menuId), "Dish id=" + id + ", menu id=" + menuId + " missed.");
    }

    public List<Dish> getAllByMenuId(int menuId) {
        ValidationUtil.checkNotFoundWithId(menuRepository.getById(menuId), menuId);
        return dishRepository.getAll(menuId);
    }

    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && get(dish.getId(), menuId) == null) {
            return null;
        }
        dish.setMenu(menuRepository.getOne(menuId));
        return dishRepository.save(dish);
    }
}