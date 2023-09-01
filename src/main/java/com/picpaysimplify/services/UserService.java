package com.picpaysimplify.services;

import com.picpaysimplify.domain.user.UserType;
import com.picpaysimplify.domain.user.User;
import com.picpaysimplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void createUser(User user){
        this.repository.save(user);
    }

    public User findUserById(String id) throws Exception {
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

}
