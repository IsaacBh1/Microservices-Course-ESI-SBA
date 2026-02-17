package com.example.ms_patient.DTOs;

import lombok.Data;

@Data
public class MedicamentDTO {
    private Long id;
    private String nomMedicament;
    private Integer duree;
    private String dosage;
    private Double cout;
}