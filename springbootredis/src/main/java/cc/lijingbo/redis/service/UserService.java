package cc.lijingbo.redis.service;


import cc.lijingbo.redis.domain.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    void delete(Integer id);

    User insertUser(String username, String address);
}
