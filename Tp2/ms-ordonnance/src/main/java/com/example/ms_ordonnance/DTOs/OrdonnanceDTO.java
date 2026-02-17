package com.example.ms_ordonnance.DTOs;

import lombok.Data;
import java.util.List;

@Data
public class OrdonnanceDTO {
    private Long id;
    private String date;
    private Long patientId;
    private List<MedicamentDTO> medicaments;
}
