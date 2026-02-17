package com.example.ms_remboursement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    
    private Double montant;
    
    private Long ordonnanceId; 
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_assure_id")
    private PatientAssure patientAssure;
}
