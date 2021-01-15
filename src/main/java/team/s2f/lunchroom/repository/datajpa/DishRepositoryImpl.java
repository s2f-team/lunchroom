package team.s2f.lunchroom.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Dish;
import team.s2f.lunchroom.repository.DishRepository;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {
    private final DishCrud dishCrud;
    private final MenuCrud menuCrud;

    @Autowired
    public DishRepositoryImpl(DishCrud dishCrud, MenuCrud menuCrud) {
        this.dishCrud = dishCrud;
        this.menuCrud = menuCrud;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int menuId) {
        if (!dish.isNew() && get(dish.getId(), menuId) == null) {
            return null;
        }
        dish.setMenu(menuCrud.getOne(menuId));
        return dishCrud.save(dish);
    }

    @Override
    public boolean delete(int id, int menuId) {
        return dishCrud.delete(id, menuId) != 0;
    }

    @Override
    public Dish get(int id, int menuId) {
        return dishCrud.findById(id, menuId);
    }

    @Override
    public Dish getByNameForMenu(String name, int menuId) {
        return dishCrud.getByNameForMenu(name, menuId);
    }


    @Override
    public List<Dish> getAllByMenu(int menuId) {
        return dishCrud.getAll(menuId);
    }
}
