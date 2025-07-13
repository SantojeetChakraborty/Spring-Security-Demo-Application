package com.example.securitydemo.controller;

import com.example.securitydemo.models.User;
import com.example.securitydemo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user ,@PathVariable String username){
        User userFromDb = userService.findByUsername(username);
        if(userFromDb!=null){
            userFromDb.setUsername(user.getUsername());
            userFromDb.setPassword(user.getPassword());
            userService.saveUser(userFromDb);
        }
        return new ResponseEntity<>(userFromDb, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public boolean deleteUser(@PathVariable ObjectId id){
        userService.deleteUser(id);
        return true;
    }
}
