package com.picpaysimplify.transaction;

import com.picpaysimplify.user.User;
import java.math.BigDecimal;
import java.util.UUID;


public record TransactionDTO(BigDecimal amount, UUID senderId, UUID receiverId) {}