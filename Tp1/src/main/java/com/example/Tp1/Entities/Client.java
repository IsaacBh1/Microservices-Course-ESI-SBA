package com.example.Tp1.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name = "";
    
    @Column(unique = true, nullable = false)
    private String email = "";

    private LocalDate birthDate = LocalDate.now();
    private String phone = "";

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.MALE;

    @Embedded
    private Address address = new Address();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>(); 
    
    public void addAccount(Account account) {
        accounts.add(account);
        account.setClient(this);
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setClient(null);
    }

    public Client(String name, String email, LocalDate birthDate, String phone, Gender gender, Address address) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
    }

    public Long getClientId() {
        return id;
    }
}