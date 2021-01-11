package team.s2f.lunchroom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import team.s2f.lunchroom.HasId;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantTo extends BaseTo implements HasId, Serializable {
    private static final long serialVersionUID = 1L;

    long votesCount;

    public RestaurantTo(Integer id, long votesCount) {
        super(id);
        this.votesCount = votesCount;
    }
}
