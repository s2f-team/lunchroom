package team.s2f.lunchroom.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.s2f.lunchroom.dto.VoteTo;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.repository.VoteRepository;
import team.s2f.lunchroom.util.ValidationUtil;
import team.s2f.lunchroom.util.VoteUtil;
import team.s2f.lunchroom.util.exception.ApplicationException;
import team.s2f.lunchroom.util.exception.ErrorType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static team.s2f.lunchroom.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private static final Logger log = getLogger(VoteService.class);
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote createOrUpdate(VoteTo voteTo, int userId) {
        Assert.notNull(voteTo, "VoteTo must not be null.");
        Vote vote = VoteUtil.createNewFromTo(voteTo, userId);

        LocalDateTime today = LocalDate.now().atStartOfDay();
        Vote existing = voteRepository.getByUserForToday(userId, today);
        if (existing != null) {
            if (vote.getDateTime().getHour() < 11) {
                vote.setId(existing.getId());
                log.info("Change vote {} by user {} for today.", vote, userId);
                return voteRepository.save(vote);
            } else {
                log.info("It's too late to voting.");
                throw new ApplicationException("It's too late to voting.", ErrorType.DUPLICATE_VOTE);
            }
        }
        log.info("New vote {} by user {}.", vote, userId);
        return voteRepository.save(vote);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    public Vote get(int id, int userId) {
        return ValidationUtil.checkNotFoundWithId(voteRepository.get(id, userId), id);
    }

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    //This method is just for testing
    public Vote createOrUpdateJustForTest(VoteTo voteTo, int userId, LocalTime requestTime) {
        Assert.notNull(voteTo, "VoteTo must not be null.");
        Vote vote = VoteUtil.createNewFromTo(voteTo, userId);
        vote.setDateTime(LocalDateTime.now().with(requestTime));

        LocalDateTime today = LocalDate.now().atStartOfDay();
        Vote existing = voteRepository.getByUserForToday(userId, today);
        if (existing != null) {
            if (vote.getDateTime().getHour() < 11) {
                vote.setId(existing.getId());
                log.info("Change vote {} by user {} for today.", vote, userId);
                return voteRepository.save(vote);
            } else {
                log.info("It's too late to voting.");
                throw new ApplicationException("It's too late to voting.", ErrorType.DUPLICATE_VOTE);
            }
        }
        log.info("New vote {} by user {}.", vote, userId);
        return voteRepository.save(vote);
    }
}
