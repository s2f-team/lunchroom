package team.s2f.lunchroom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import team.s2f.lunchroom.HasId;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoteTo extends BaseTo implements HasId, Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private int restaurantId;

    @NotNull
    private int menuId;

    public VoteTo(@NotNull int restaurantId, @NotNull int menuId) {
        this(null, restaurantId, menuId);
    }

    public VoteTo(Integer id, @NotNull int restaurantId, @NotNull int menuId) {
        super(id);
        this.restaurantId = restaurantId;
        this.menuId = menuId;
    }
}
