package com.picpaysimplify.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(BigDecimal amount, UUID sender_id, UUID receiver_id) {}
