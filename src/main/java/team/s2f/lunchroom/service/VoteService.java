package team.s2f.lunchroom.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.repository.RestaurantRepository;
import team.s2f.lunchroom.repository.VoteRepository;
import team.s2f.lunchroom.util.ValidationUtil;
import team.s2f.lunchroom.util.VoteUtil;
import team.s2f.lunchroom.util.exception.ApplicationException;
import team.s2f.lunchroom.util.exception.DuplicateVoteException;
import team.s2f.lunchroom.util.exception.ErrorType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static team.s2f.lunchroom.util.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class VoteService {
    private static final Logger log = getLogger(VoteService.class);
    private static final LocalTime endOfVoting = LocalTime.of(11, 0);

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public Vote createOrUpdate(VoteTo voteTo, int userId) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.getById(voteTo.getRestaurantId()), voteTo.getRestaurantId());
        Assert.notNull(voteTo, "VoteTo must not be null.");
        Vote vote = VoteUtil.createNewFromTo(voteTo, userId);

        Vote existing = voteRepository.getFromUserFromDate(userId, LocalDate.now()).orElse(null);
        if (existing != null) {
            if (LocalTime.now().isBefore(endOfVoting)) {
                vote.setId(existing.getId());
                log.info("Change vote {} by user {} for today.", vote, userId);
                return voteRepository.save(vote);
            } else {
                log.info("It's too late to voting.");
                throw new DuplicateVoteException("It's too late to voting.");
            }
        }
        log.info("New vote {} by user {}.", vote, userId);
        return voteRepository.save(vote);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    public Vote get(int id, int userId) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.findByIdAndUserId(id, userId), id);
    }

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    //This method is only for testing
    public Vote createOrUpdateJustForTest(VoteTo voteTo, int userId, LocalTime requestTime) {
        ValidationUtil.checkNotFoundWithId(restaurantRepository.getById(voteTo.getRestaurantId()), voteTo.getRestaurantId());
        Assert.notNull(voteTo, "VoteTo must not be null.");
        Vote vote = VoteUtil.createNewFromTo(voteTo, userId);
        vote.setDate(LocalDate.now());

        Vote existing = voteRepository.getFromUserFromDate(userId, LocalDate.now()).orElse(null);
        if (existing != null) {
            if (requestTime.isBefore(endOfVoting)) {
                vote.setId(existing.getId());
                log.info("Change vote {} by user {} for today.", vote, userId);
                return voteRepository.save(vote);
            } else {
                log.info("It's too late to voting.");
                throw new DuplicateVoteException("It's too late to voting.");
            }
        }
        log.info("New vote {} by user {}.", vote, userId);
        return voteRepository.save(vote);
    }
}