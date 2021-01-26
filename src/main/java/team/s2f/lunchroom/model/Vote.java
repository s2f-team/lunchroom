package team.s2f.lunchroom.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "created"}, name = "vote_unique_user_created_idx")})
//CREATE UNIQUE INDEX vote_unique_user_created_idx ON vote (user_id, created);
public class Vote extends AbstractBaseEntity {
    @Column(name = "created", nullable = false)
    @NotNull
    private LocalDate date;

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
        this(null, LocalDate.now(), userId, restaurantId, menuId);
    }

    public Vote(Integer id, LocalDate date, Integer userId, Integer restaurantId, Integer menuId) {
        super(id);
        this.date = date;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", menuId=" + menuId +
                '}';
    }
}
