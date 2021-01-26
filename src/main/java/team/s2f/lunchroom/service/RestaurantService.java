package team.s2f.lunchroom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.repository.RestaurantRepository;
import team.s2f.lunchroom.util.ValidationUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null.");
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.getById(id), id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null.");
        ValidationUtil.checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    public void delete(int id) {
     //   ValidationUtil.checkNotFoundWithId(restaurantRepository.delete(id), id);
        ValidationUtil.checkSingleModification(restaurantRepository.delete(id), "Restaurant id=" + id + " missed.");
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }
}
