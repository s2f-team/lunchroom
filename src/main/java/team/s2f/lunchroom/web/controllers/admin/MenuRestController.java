package team.s2f.lunchroom.web.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import team.s2f.lunchroom.model.Menu;
import team.s2f.lunchroom.service.MenuService;
import team.s2f.lunchroom.service.RestaurantService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rest/admin/restaurants/{restaurantId}/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {
    private static final Logger log = getLogger(MenuRestController.class);

    private final MenuService menuService;
    private final RestaurantService restaurantService;

    //Create menu
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Menu> create(@PathVariable("restaurantId") int restaurantId) {
        Menu menu = new Menu(restaurantService.getOne(restaurantId));
        log.info("Create new menu for restaurant id {} {}.", restaurantId, LocalDate.now());
        Menu created = menuService.create(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/restaurants/{restaurantId}/menus/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    //Delete menu with dishes
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("Delete menu by id {}", id);
        menuService.delete(id);
    }

    //Get Menu with dishes by restaurantId
    //Мне кажется, это не по REST!!
    @GetMapping(value = "/byrestaurant")
    public Menu getWithDishesByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        log.info("Get menu with dishes by restaurant id {}.", restaurantId);
        return menuService.getByRestaurant(restaurantId, LocalDate.now());
    }

    //Get all menus with dishes and restaurants by date
    @GetMapping(value = "/by")
    public List<Menu> getAllWithRestaurantsByDate(@RequestParam("date") String date) {
        return menuService.getAllWithRestaurants(LocalDate.parse(date));
    }
}