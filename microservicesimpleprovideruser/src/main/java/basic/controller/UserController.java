package basic.controller;

import basic.dao.UserRepository;
import basic.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 15:48 2019/11/19
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        User findOne = userRepository.findById(id).get();
        return findOne;
    }
}
