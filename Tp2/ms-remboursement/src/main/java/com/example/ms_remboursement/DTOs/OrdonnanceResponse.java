package com.example.ms_remboursement.DTOs;

import java.time.LocalDate;
import java.util.List;

public class OrdonnanceResponse {
    private Long id;
    private LocalDate date;
    private List<MedicamentResponse> medicaments;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public List<MedicamentResponse> getMedicaments() {
        return medicaments;
    }
    public void setMedicaments(List<MedicamentResponse> medicaments) {
        this.medicaments = medicaments;
    }

}