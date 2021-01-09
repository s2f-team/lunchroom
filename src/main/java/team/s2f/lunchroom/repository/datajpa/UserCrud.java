package team.s2f.lunchroom.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import team.s2f.lunchroom.model.User;

@Transactional(readOnly = true)
public interface UserCrud extends JpaRepository<User, Integer> {

    User getByEmail(String email);

  //  int delete( int id);
}
