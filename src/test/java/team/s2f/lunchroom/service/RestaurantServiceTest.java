package team.s2f.lunchroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import team.s2f.lunchroom.RestaurantTestData;
import team.s2f.lunchroom.model.Restaurant;

public class RestaurantServiceTest {
    @Autowired
    protected RestaurantService restaurantService;

    void create() {
        Restaurant created = restaurantService.create(RestaurantTestData.getNew());
    }

    void createWithException() {
    }

    void update() {
    }

    void get() {
    }

    void delete() {
    }
}
