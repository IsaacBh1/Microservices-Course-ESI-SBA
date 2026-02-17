package com.example.ms_patient.DTOs;

import java.util.List;

public class OrdonnanceDetailDTO {
    private Long idOrdonnance;
    private List<MedicamentDTO> medicaments;
    private RemboursementDTO remboursement;
    
    public Long getIdOrdonnance() { return idOrdonnance; }
    public void setIdOrdonnance(Long idOrdonnance) { this.idOrdonnance = idOrdonnance; }
    public List<MedicamentDTO> getMedicaments() { return medicaments; }
    public void setMedicaments(List<MedicamentDTO> medicaments) { this.medicaments = medicaments; }
    public RemboursementDTO getRemboursement() { return remboursement; }
    public void setRemboursement(RemboursementDTO remboursement) { this.remboursement = remboursement; }
}