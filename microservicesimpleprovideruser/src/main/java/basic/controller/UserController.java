package basic.controller;

import basic.dao.UserRepository;
import basic.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 15:48 2019/11/19
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

//    @GetMapping("/{id}")
//    public User findById(@PathVariable Long id) {
//        User findOne = userRepository.findById(id).get();
//        return findOne;
//    }

    @GetMapping("/get")
    public User get1(User user) {
        return user;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        return user;
    }

    @GetMapping("/get333")
    public User get333(User user) {
        return user;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        if ("favicon.ico".equals(id))
            return null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            UserDetails user = (UserDetails) principal;
//            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
//            for (GrantedAuthority c: collection) {
//                // print the current user information
//                UserController.LOGGER.info("当前用户是{}, 角色是{}", user.getUsername(), c.getAuthority());
//            }
//        }
//        else {
//            // do other things
//        }
        User findOne = this.userRepository.findById(id).get();
        return findOne;

    }
}
