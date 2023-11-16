package com.example.dao.mapper;

import com.example.dao.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface UserMapper {
    public List<User> getAllUsers();

    int addUser(User user);
    int deleteUser(String username);
    User getUserByUsernameAndPassword();
    int updateUser(User user);
    User getUserByUsername(String un);

}
