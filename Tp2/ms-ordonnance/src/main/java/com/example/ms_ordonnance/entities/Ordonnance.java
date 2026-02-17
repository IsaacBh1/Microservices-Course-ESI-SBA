package com.example.ms_ordonnance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    
    private Long patientId;
    
    @OneToMany(mappedBy = "ordonnance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicament> medicaments;
}
