package com.example.ms_ordonnance.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
    private Long id;
    private String nom;
    private String numeroSecuriteSocial;
    private Double plafondRemboursement;
}