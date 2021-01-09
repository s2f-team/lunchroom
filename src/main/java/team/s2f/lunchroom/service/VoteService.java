package team.s2f.lunchroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.repository.VoteRepository;

import static team.s2f.lunchroom.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote create(Vote vote) {
        Assert.notNull(vote, "Vote must not be null.");
        return voteRepository.save(vote);
    }

    public void update(Vote vote) {
        Assert.notNull(vote, "Vote must not be null.");
        checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id), id);
    }
}
