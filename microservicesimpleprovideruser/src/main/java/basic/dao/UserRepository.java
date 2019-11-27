package basic.dao;

import basic.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 10:43 2019/11/27
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //public User findById(int id);
}
