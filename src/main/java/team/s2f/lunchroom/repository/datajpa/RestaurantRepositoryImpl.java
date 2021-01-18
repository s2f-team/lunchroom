package team.s2f.lunchroom.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final RestaurantCrud restaurantCrud;

    @Autowired
    public RestaurantRepositoryImpl(RestaurantCrud restaurantCrud) {
        this.restaurantCrud = restaurantCrud;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantCrud.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return restaurantCrud.delete(id) != 0;
    }

    @Override
    public Restaurant getOne(int id) {
        return restaurantCrud.getOne(id);
    }

    @Override
    public Restaurant getById(Integer id) {
        return restaurantCrud.getById(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantCrud.findAll();
    }
}
