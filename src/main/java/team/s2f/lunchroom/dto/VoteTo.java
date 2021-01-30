package team.s2f.lunchroom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoteTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private int restaurantId;

    public VoteTo(@NotNull int restaurantId) {
        this.restaurantId = restaurantId;
    }
}