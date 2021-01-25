package team.s2f.lunchroom.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import team.s2f.lunchroom.RestaurantTestData;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

public class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    protected RestaurantService restaurantService;

    @Test
    void create() {
        Restaurant created = restaurantService.create(RestaurantTestData.getNew());
        int newId = created.id();
        Restaurant newRestaurant = RestaurantTestData.getNew();
        newRestaurant.setId(newId);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void createDuplicate() {
        Assertions.assertThrows(DataAccessException.class, () -> restaurantService.create(RestaurantTestData.getNewDuplicate()));
    }

    @Test
    void createWithException() {
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "A", "+7(000)975-46-23", "Moscow, Leskova, 27", "website")), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "   ", "+7(000)975-46-23", "Moscow, Leskova, 27", "website")), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "New", "975-46-23", "Moscow, Leskova, 27", "website")), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "New", "975-46-23975-46-23975-46-23", "Moscow, Leskova, 27", "website")), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "New", "975-46-23975-46-23975-46-23", "Address", "website")), ConstraintViolationException.class);
        validateRootCause(() -> restaurantService.create(new Restaurant(null, "New", "975-46-23975-46-23975-46-23", "Address", ".ru")), ConstraintViolationException.class);
    }

    @Test
    void update() {
        Restaurant updated = RestaurantTestData.getUpdated();
        restaurantService.update(updated);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurantService.get(RestaurantTestData.FISH_HOUSE_ID), updated);
    }

    @Test
    void delete() {
        restaurantService.delete(RestaurantTestData.FISH_HOUSE_ID);
        Assertions.assertThrows(NotFoundException.class, () -> restaurantService.get(RestaurantTestData.FISH_HOUSE_ID));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> restaurantService.get(RestaurantTestData.NOT_FOUND));
    }

    @Test
    void get() {
        Restaurant actual = restaurantService.get(RestaurantTestData.FISH_HOUSE_ID);
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(actual, RestaurantTestData.fish_house);
    }

    @Test
    void getNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> restaurantService.get(RestaurantTestData.NOT_FOUND));
    }

    @Test
    void getAll() {
        RestaurantTestData.RESTAURANT_MATCHER.assertMatch(restaurantService.getAll(), RestaurantTestData.getAll);
    }
}