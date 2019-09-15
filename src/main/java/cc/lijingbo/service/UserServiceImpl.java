package cc.lijingbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import cc.lijingbo.domain.User;
import cc.lijingbo.mapper.UserMapper;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "UserCache", key = "'user.getAllUsers'")
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    @CacheEvict(value = "UserCache", key = "'user.getAllUsers'")
    public void delete(Integer id) {
        System.out.println("删除了 id 为" + id + "的用户");
        userMapper.delete(id);
    }

    @Override
    @CacheEvict(value = "UserCache", key = "'user.getAllUsers'")
    public User insertUser(String username, String address) {
        userMapper.insertUser(username, address);
        return null;
    }
}
