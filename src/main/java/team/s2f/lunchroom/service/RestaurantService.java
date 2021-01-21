package team.s2f.lunchroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.repository.RestaurantRepository;
import team.s2f.lunchroom.util.ValidationUtil;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null.");
        return repository.save(restaurant);
    }

    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.getById(id), id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null.");
        ValidationUtil.checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
