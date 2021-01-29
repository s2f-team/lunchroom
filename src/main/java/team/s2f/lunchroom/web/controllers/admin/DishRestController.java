package team.s2f.lunchroom.web.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import team.s2f.lunchroom.model.Dish;
import team.s2f.lunchroom.service.DishService;
import team.s2f.lunchroom.service.MenuService;
import team.s2f.lunchroom.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/rest/admin/restaurants/{restaurantId}/menu/{menuId}/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    private static final Logger log = getLogger(DishRestController.class);

    private final DishService dishService;

    //Create new dish
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @PathVariable Integer restaurantId, @PathVariable Integer menuId) {
        ValidationUtil.checkNew(dish);
        log.info("Create new dish {} for menu {}", dish, menuId);
        Dish created = dishService.create(dish, menuId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/admin/restaurants/{restaurantId}/menu/{menuId}/dishes/{id}")
                .buildAndExpand(restaurantId, menuId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    //Update dish id {}
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable int id, @PathVariable int menuId) {
        ValidationUtil.assureIdConsistent(dish, id);
        log.info("Update dish {}.", dish);
        dishService.update(dish, menuId);
    }

    //Delete dish id {}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int menuId) {
        log.info("Delete dish by id {}.", id);
        dishService.delete(id, menuId);
    }

    //Get dish by id and menuId
    @GetMapping(value = "/{id}")
    public Dish get(@PathVariable int id, @PathVariable int menuId) {
        log.info("Get dish by id {}.", id);
        return dishService.get(id, menuId);
    }

    //Get all dishes by menuId
    @GetMapping()
    public List<Dish> getAllByMenuId(@PathVariable int menuId) {
        log.info("Get all dishes by menu id {}.", menuId);
        return dishService.getAllByMenuId(menuId);
    }
}