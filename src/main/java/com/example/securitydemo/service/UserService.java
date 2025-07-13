package com.example.securitydemo.service;

import com.example.securitydemo.models.User;
import com.example.securitydemo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    }

    public User updateUser(ObjectId id, User user){
        userRepository.save(user);
        return user;
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
