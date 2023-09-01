package com.picpaysimplify.repositories;

import com.picpaysimplify.domain.trasactions.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
