package com.picpaysimplify.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findBySenderId(UUID id);

    List<Transaction> findByReceiverId(UUID id);
}
