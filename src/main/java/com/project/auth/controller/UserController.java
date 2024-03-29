package com.project.auth.controller;

import com.project.auth.entity.User;
import com.project.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id){
        userService.deleteById(id);
        return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> postUser(@RequestBody User user){
        if (user!=null){
            userService.postUser(user);
            return new ResponseEntity<>("User successfully saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid User", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
