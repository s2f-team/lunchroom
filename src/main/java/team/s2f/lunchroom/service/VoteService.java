package team.s2f.lunchroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.repository.VoteRepository;

import java.time.LocalDateTime;

import static team.s2f.lunchroom.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote createOrUpdate(Vote vote) {
        Assert.notNull(vote, "Vote must not be null.");
        return voteRepository.save(vote);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(voteRepository.delete(id, userId), id);
    }

    public Vote getByUserForToday(int userId, LocalDateTime startOfDay) {
        return voteRepository.getByUserForToday(userId, startOfDay);
    }
}
