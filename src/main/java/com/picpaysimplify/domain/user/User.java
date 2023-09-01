package com.picpaysimplify.domain.user;

import com.picpaysimplify.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO userData){
        this.userType = userData.userType();
        this.first_name = userData.first_name();
        this.last_name = userData.last_name();
        this.balance = userData.balance();
        this.email = userData.email();
        this.password = userData.password();
    }
}
