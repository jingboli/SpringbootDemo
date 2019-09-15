package cc.lijingbo.service;

import java.util.List;

import cc.lijingbo.domain.User;


public interface UserService {

    List<User> getAllUsers();

    void delete(Integer id);

    User insertUser(String username, String address);
}
