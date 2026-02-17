package com.example.ms_ordonnance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomMedicament;
    
    private Integer duree; 
    
    private String dosage;
    
    private Double cout;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ordonnance_id")
    private Ordonnance ordonnance;
}
