package com.example.springsecuritytest.Service;

import com.example.springsecuritytest.Entity.Role;
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
public class UserServiceImpl implements UserDetailsService,UserService {
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
    @Override
    public User findByUsername(String name){
        return userRepository.findByUsername(name);
    }
    @Override
    public User findUserById(int id){
       return userRepository.findById(id).orElseThrow(()->
               new UsernameNotFoundException("User with id "+ id + "not found"));
    }
    @Override
    public boolean removeById(int id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    @Transactional
    public boolean saveUser(User user)  {
        if((userRepository.findByUsername(user.getUsername()))== null){
            user.setRoles(Collections.singleton(new Role("ROLE_USER")));
            user.setPassword(encoder.getPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return  true;
        }
        return false;
    }
    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }
}
