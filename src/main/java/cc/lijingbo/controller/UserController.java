package cc.lijingbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cc.lijingbo.domain.User;
import cc.lijingbo.service.UserService;


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
