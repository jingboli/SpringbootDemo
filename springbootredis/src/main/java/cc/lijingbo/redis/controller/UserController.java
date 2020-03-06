package cc.lijingbo.redis.controller;


import cc.lijingbo.redis.domain.User;
import cc.lijingbo.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/userList")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User insertUser(@RequestParam String username, String address) {
       return userService.insertUser(username,address);
    }


}
