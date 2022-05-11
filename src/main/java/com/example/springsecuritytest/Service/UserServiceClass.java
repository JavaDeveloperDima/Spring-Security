package com.example.springsecuritytest.Service;

import com.example.springsecuritytest.Entity.Role;
import com.example.springsecuritytest.Excaption.UserHaveAlreadyExists;
import com.example.springsecuritytest.Excaption.UserNotFoundExcaption;
import com.example.springsecuritytest.Repositories.UserRepository;
import com.example.springsecuritytest.Entity.User;
import com.example.springsecuritytest.config.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceClass implements UserDetailsService {
    @Autowired
    private Encoder encoder;
    @Autowired
    private  UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User " +username+" not found");
        }
        return user;
    }
    public User findByUsername(String name){
        return userRepository.findByUsername(name);
    }

    public User findUserById(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new UsernameNotFoundException("Пользователь с id "+id+" не найден");
        }
    }
    public boolean removeById(int id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
    public boolean saveUser(User user)  {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null){
            return false;
        }else {
            user.setRoles(Collections.singleton(new Role("ROLE_USER")));
            user.setPassword(encoder.getPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return  true;
        }
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
}
