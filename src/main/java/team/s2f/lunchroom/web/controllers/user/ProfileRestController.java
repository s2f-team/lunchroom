package team.s2f.lunchroom.web.controllers.user;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import team.s2f.lunchroom.AuthorizedUser;
import team.s2f.lunchroom.dto.UserTo;
import team.s2f.lunchroom.model.User;
import team.s2f.lunchroom.service.UserService;
import team.s2f.lunchroom.util.UserUtil;

import java.net.URI;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/rest/profile")
public class ProfileRestController {
    private static final Logger log = getLogger(ProfileRestController.class);

    private final UserService userService;

    @Autowired
    public ProfileRestController(UserService userService) {
        this.userService = userService;
    }

    //Register new user
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserTo userTo) {
        log.info("Create new user from to {}", userTo);
        User created = userService.create(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/restaurants/menu").build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    //Get profile
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserTo getTo(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("Get userTo by id {}.", authUser.getId());
        return UserUtil.asTo(userService.get(authUser.getId()));
    }

    //Update profile
    //doesn't work
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authUser) throws BindException {
        log.info("Update user to {} by user id {}.", userTo, authUser.getId());
        //переписать на валидацию
        if (userTo.getId() == authUser.getId()) {
            userService.update(userTo);
        }
    }

    //Delete profile
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("Delete profile id {} by user.", authUser.getId());
        userService.delete(authUser.getId());
    }
}
