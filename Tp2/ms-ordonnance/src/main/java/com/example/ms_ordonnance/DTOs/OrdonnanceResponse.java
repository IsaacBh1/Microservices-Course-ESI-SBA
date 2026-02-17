package com.example.ms_ordonnance.DTOs;

import java.util.List;

// DTOs
public class OrdonnanceResponse {
    private Long idOrdonnance;
    private String date;
    private List<MedicamentResponse> medicaments;
    
    public Long getIdOrdonnance() { return idOrdonnance; }
    public void setIdOrdonnance(Long idOrdonnance) { this.idOrdonnance = idOrdonnance; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public List<MedicamentResponse> getMedicaments() { return medicaments; }
    public void setMedicaments(List<MedicamentResponse> medicaments) { this.medicaments = medicaments; }
}