package com.picpaysimplify.domain.trasactions;

import com.picpaysimplify.domain.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="transaction_id")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long transaction_id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="sender_id")
    private Users sender;
    @ManyToOne
    @JoinColumn(name="receiver_id")
    private Users receiver;
    private LocalDateTime create_at;
}
