package com.example.Tp1.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;  

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String login;
    
    private String password;
    private String role;
    
    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference  
    private Client client;

    public Account(String login, String password, String role, boolean isActive, LocalDateTime creationDate, Client client) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.creationDate = creationDate;
        this.client = client;
    }
}