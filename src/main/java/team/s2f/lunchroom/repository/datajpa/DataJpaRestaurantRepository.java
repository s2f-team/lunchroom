package team.s2f.lunchroom.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaRestaurantRepository  {
    /*private final CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }


    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() != null) {
            Restaurant existing = this.getById(restaurant.getId());
            if (existing == null) {
                return null;
            }
        }
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public void delete(Integer id) {
        crudRestaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant getById(Integer id) {
        return crudRestaurantRepository.getById(id);
    }

    @Override
    public Restaurant getByName(String name) {
        return crudRestaurantRepository.getByName(name);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getActualWithMenu(LocalDate date) {
        return null;
    }

  *//*  @Override
    public List<Restaurant> getActualWithMenu(LocalDate date) {
        return crudRestaurantRepository.getActualWithMenu(date);
    }*//*

    *//*@Override
    public List<Restaurant> getWithDishes() {
        return crudRestaurantRepository.getAllWithDishes();
    }*/
}
