package com.picpaysimplify.transaction;

import com.picpaysimplify.user.User;
import com.picpaysimplify.notification.NotificationService;
import com.picpaysimplify.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {

        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.amount());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.amount());

        if(!isAuthorized){
            throw new Exception("Transaction not authorized");
        }
        Transaction newTransaction = new Transaction(transaction.amount(), sender, receiver);

        sender.setBalance(sender.getBalance().subtract(transaction.amount()));
        receiver.setBalance(receiver.getBalance().add(transaction.amount()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transaction sent");
        this.notificationService.sendNotification(receiver, "Transaction received");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal amount){
        if(sender.getBalance().compareTo(amount) >= 0) {
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

            if(authorizationResponse.getStatusCode() == HttpStatus.OK){
                String message = (String) authorizationResponse.getBody().get("message");
                return "Autorizado".equalsIgnoreCase(message);
            }
        }
        return false;
    }

    public List<Transaction> getAllTransactionsByUserId(UUID id) throws Exception {
        User user = this.userService.findUserById(id);
        switch(user.getUserType()){
            case MERCHANT -> {
                return this.getAllTransactionsByReceiver(id);
            }
            case COMMON -> {
                return this.getAllTransactionsBySender(id);
            }
        }
        return new ArrayList<>();
    }

    public List<Transaction> getAllTransactionsBySender(UUID id) {
        return this.transactionRepository.findBySenderId(id);
    }

    public List<Transaction> getAllTransactionsByReceiver(UUID id) {
        return this.transactionRepository.findByReceiverId(id);
    }

    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }
}
