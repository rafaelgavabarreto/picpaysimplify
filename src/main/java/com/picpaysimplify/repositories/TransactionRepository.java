package com.picpaysimplify.repositories;

import com.picpaysimplify.domain.trasaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
