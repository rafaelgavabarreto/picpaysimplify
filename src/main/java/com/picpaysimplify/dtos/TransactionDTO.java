package com.picpaysimplify.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount, String sender_id, String receiver_id) {
}
