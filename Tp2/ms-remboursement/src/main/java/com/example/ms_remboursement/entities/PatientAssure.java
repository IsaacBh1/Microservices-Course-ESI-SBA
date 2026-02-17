package com.example.ms_remboursement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientAssure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numeroSecuriteSocial;
    
    private String nom;
    
    private Long patientId; 
    
    @OneToMany(mappedBy = "patientAssure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Remboursement> remboursements;
}
