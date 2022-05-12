package com.example.springsecuritytest.Service;

import com.example.springsecuritytest.Entity.User;

import java.util.List;

public interface UserService {
    public User findByUsername(String name);
    public User findUserById(int id);
    public boolean removeById(int id);
    public boolean saveUser(User user);
    public List<User> getAll();
}
