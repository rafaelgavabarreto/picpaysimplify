package com.picpaysimplify.transaction;

import com.picpaysimplify.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="senderId")
    private User sender;
    @ManyToOne
    @JoinColumn(name="receiverId")
    private User receiver;
    private LocalDateTime timestamp;

    public Transaction(BigDecimal amount, User sender, User receiver, LocalDateTime now) {
    }
}
