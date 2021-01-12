package team.s2f.lunchroom.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "user_id", nullable = false)
    @NotNull
    private int userId;

    @Column(name = "rest_id", nullable = false)
    @NotNull
    private int restaurantId;

    @Column(name = "menu_id", nullable = false)
    @NotNull
    private int menuId;

    public Vote(Integer userId, Integer restaurantId, Integer menuId) {
        this(null, LocalDateTime.now(), userId, restaurantId, menuId);
    }

    public Vote(Integer id, LocalDateTime dateTime, Integer userId, Integer restaurantId, Integer menuId) {
        super(id);
        this.dateTime = LocalDateTime.now();
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.menuId = menuId;
    }
}
