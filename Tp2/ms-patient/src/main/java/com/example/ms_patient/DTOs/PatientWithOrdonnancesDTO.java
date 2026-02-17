package com.example.ms_patient.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientWithOrdonnancesDTO {
    private Long id;
    private String nom;
    private String numeroSecuriteSocial;
    private Double plafondRemboursement;
    private List<OrdonnanceDTO> ordonnances;
}