package com.example.ms_patient.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdonnanceDTO {
    private Long idOrdonnance;
    private List<MedicamentDTO> medicaments;
    private RemboursementDTO remboursement;
}