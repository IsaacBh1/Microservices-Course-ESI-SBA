package com.example.ms_patient.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntecedentMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String maladie;
    
    private LocalDate dateDiagnostic;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
