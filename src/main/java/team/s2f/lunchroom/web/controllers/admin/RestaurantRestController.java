package team.s2f.lunchroom.web.controllers.admin;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import team.s2f.lunchroom.dto.RestaurantTo;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.service.RestaurantService;
import team.s2f.lunchroom.service.VoteService;
import team.s2f.lunchroom.util.RestaurantUtil;
import team.s2f.lunchroom.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("rest/admin/restaurants")
public class RestaurantRestController {
    private static final Logger log = getLogger(RestaurantRestController.class);

    private final RestaurantService restaurantService;
    private final VoteService voteService;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService, VoteService voteService) {
        this.restaurantService = restaurantService;
        this.voteService = voteService;
    }

    //Create new restaurant
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        ValidationUtil.checkNew(restaurant);
        log.info("Create new restaurant - {}.", restaurant);
        return restaurantService.create(restaurant);
    }

    //Update restaurant by id
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("Update restaurant {}.", restaurant);
        restaurantService.update(restaurant);
    }

    //Delete restaurant by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("Delete restaurant by id {}.", id);
        restaurantService.delete(id);
    }

    //Get restaurant by id
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int id) {
        log.info("Get restaurant by id {}.", id);
        return restaurantService.get(id);
    }

    //Get all restaurants without menu
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        log.info("Get all restaurants.");
        return restaurantService.getAll();
    }

    //Get restaurants where menu exists without menu
    @GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getActualWithMenu() {
        log.info("Get actual restaurants with menu for today.");
        LocalDate date = LocalDate.now();
        return restaurantService.getActualWithMenu(date);
    }

    //Get restaurantId and votes count
    @GetMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getWithVotes() {
        return RestaurantUtil.getTos(voteService.getAll(), restaurantService.getAll());
    }

}
