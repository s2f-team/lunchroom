package team.s2f.lunchroom.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


//Generating equals/hashCode implementation but without a call to superclass, even though this class does not extend java.lang.Object. If this is intentional, add '(callSuper=false)' to your type.
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    @Column(name = "date")
    private LocalDateTime dateTime;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "rest_id")
    private int restaurantId;

    @Column(name = "menu_id")
    private int menuId;

    public Vote(Integer userId, Integer restaurantId, Integer menuId) {
        this(null, userId, restaurantId, menuId);
    }

    public Vote(Integer id, Integer userId, Integer restaurantId, Integer menuId) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.menuId = menuId;
    }
}
