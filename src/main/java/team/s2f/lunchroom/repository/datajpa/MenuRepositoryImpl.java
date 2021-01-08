package team.s2f.lunchroom.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.repository.MenuRepository;

import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    @Autowired
    private CrudMenuRepository crudMenuRepository;


    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.findAll();
    }

    @Override
    public List<Menu> getAllWithRestaurant() {
        return crudMenuRepository.getAllWithRestaurant();
    }


}
