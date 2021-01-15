package team.s2f.lunchroom.repository;

import team.s2f.lunchroom.model.Restaurant;

import java.util.List;


public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant getOne(int id);

    Restaurant getById(Integer id);

    List<Restaurant> getAll();
}
