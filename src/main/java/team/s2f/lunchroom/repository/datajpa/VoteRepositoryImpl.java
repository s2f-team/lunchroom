package team.s2f.lunchroom.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.Vote;
import team.s2f.lunchroom.repository.VoteRepository;

@Repository
public class VoteRepositoryImpl implements VoteRepository {
    private final VoteCrud voteCrud;

    @Autowired
    public VoteRepositoryImpl(VoteCrud voteCrud) {
        this.voteCrud = voteCrud;
    }

    @Override
    @Transactional
    public Vote save(Vote vote) {
        return voteCrud.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return voteCrud.delete(id, userId) != 0;
    }
}
