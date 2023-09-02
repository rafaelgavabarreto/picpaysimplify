package com.picpaysimplify.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUserId(@PathVariable UUID id) throws Exception {
        List<Transaction> newTransaction = this.transactionService.getAllTransactionsByUserId(id);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> newTransaction = this.transactionService.getAllTransactions();
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
