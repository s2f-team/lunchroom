package team.s2f.lunchroom.web.restaurant;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.service.RestaurantService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {
    private static final Logger log = getLogger(RestaurantRestController.class);
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

   /* @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAll() {
        log.info("RestaurantRestController - getAll()");
        List<Restaurant> restaurants = restaurantService.getAll();
        System.out.println(restaurants);
        return restaurants;
    }*/


   /* @GetMapping(value = "/d", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAllWithDishes() {
        log.info("RestaurantRestController - getAllWithDishes()");
        List<Restaurant> restaurants = restaurantService.getWithDishes();
        System.out.println(restaurants);
        return restaurants;
    }*/



}
