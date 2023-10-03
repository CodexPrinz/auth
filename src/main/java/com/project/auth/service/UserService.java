package com.project.auth.service;

import com.project.auth.entity.User;
import com.project.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUsers(){
        logger.info("get all users");
        return (List<User>) userRepository.findAll();
    }

    public void postUser(User user){
        logger.info("post user");
        userRepository.save(user);
        logger.info(user.toString());
    }

    public Optional<User> getUserById(Long id){
        logger.info("get user by id");
        return userRepository.findById(id);
    }

    public void deleteById(Long id){
        logger.info("delete by id");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            logger.info(user.toString());
            userRepository.deleteById(id);
        }
    }

}
