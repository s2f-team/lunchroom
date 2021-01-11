package team.s2f.lunchroom.util;

import team.s2f.lunchroom.dto.RestaurantTo;
import team.s2f.lunchroom.model.Restaurant;
import team.s2f.lunchroom.model.Vote;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static List<RestaurantTo> getTos(Collection<Vote> votes, Collection<Restaurant> restaurants) {
        Map<Integer, Long> restaurantsWithVotesCount = votes.stream()
                .collect(Collectors.groupingBy(Vote::getRestaurantId, Collectors.counting()));

        return restaurants.stream()
                .map(r -> createTo(r.getId(),
                        restaurantsWithVotesCount.containsKey(r.getId()) ? restaurantsWithVotesCount.get(r.getId()) : 0))
                .collect(Collectors.toList());
    }

    public static RestaurantTo createTo(int restaurantId, long countVotes) {
        return new RestaurantTo(restaurantId, countVotes);
    }
}
