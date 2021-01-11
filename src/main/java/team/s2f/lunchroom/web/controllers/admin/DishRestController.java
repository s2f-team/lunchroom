package team.s2f.lunchroom.web.controllers.admin;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import team.s2f.lunchroom.model.Dish;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.service.DishService;
import team.s2f.lunchroom.service.MenuService;
import team.s2f.lunchroom.service.RestaurantService;
import team.s2f.lunchroom.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/rest/admin/restaurants/{restaurantId}/menu/{menuId}/dishes")
public class DishRestController {
    private static final Logger log = getLogger(DishRestController.class);

    private final DishService dishService;
    private final MenuService menuService;
    private final RestaurantService restaurantService;

    @Autowired
    public DishRestController(DishService dishService, MenuService menuService, RestaurantService restaurantService) {
        this.dishService = dishService;
        this.menuService = menuService;
        this.restaurantService = restaurantService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish create(@RequestBody Dish dish, @PathVariable Integer restaurantId, @PathVariable @Nullable Integer menuId) {
        ValidationUtil.checkNew(dish);
        if (menuId == 0) {
            //  Restaurant restaurant = restaurantService.get(restaurantId);
            Menu menu = new Menu(LocalDate.now());
            log.info("Create new menu for restaurant id {} {}.", restaurantId, LocalDate.now());
            menuId = menuService.create(menu, restaurantId).getId();
           /* restaurant.setMenu(menu);
            restaurantService.update(restaurant);
            System.out.println(restaurant);*/
        }
        log.info("Create new dish {} for menu {}", dish, menuId);
        return dishService.create(dish, menuId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable int id, @PathVariable int menuId) {
        ValidationUtil.assureIdConsistent(dish, id);
        log.info("Update dish {}.", dish);
        dishService.update(dish, menuId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int menuId) {
        log.info("Delete dish by id {}.", id);
        dishService.delete(id, menuId);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable int id, @PathVariable int menuId) {
        log.info("Get dish by id {}.", id);
        return dishService.get(id, menuId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByMenuId(@PathVariable int menuId) {
        log.info("Get all dishes by menu id {}.", menuId);
        return dishService.getAllByMenuId(menuId);
    }


}