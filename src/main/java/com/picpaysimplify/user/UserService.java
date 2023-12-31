package com.picpaysimplify.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User createUser(UserDTO userData) throws Exception {
        User newUser = new User(userData);
        try {
            this.saveUser(newUser);
            return newUser;
        } catch (DataAccessException exception) {
            throw new Exception("User already exist");
        }
    }

    public void saveUser(User user) throws Exception {
        try {
            this.repository.save(user);
        } catch (DataAccessException exception) {
            throw new Exception("System couldn't save the user");
        }
    }

    public User findUserById(UUID id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Merchant users cannot transfer");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Insufficient amount");
        }
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
