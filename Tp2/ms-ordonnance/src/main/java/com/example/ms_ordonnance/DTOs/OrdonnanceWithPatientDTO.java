package com.example.ms_ordonnance.DTOs;

import java.util.List;

public class OrdonnanceWithPatientDTO {
    private Long id;
    private String date;
    private String patientNom;
    private List<MedicamentDTO> medicaments;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getPatientNom() { return patientNom; }
    public void setPatientNom(String patientNom) { this.patientNom = patientNom; }
    public List<MedicamentDTO> getMedicaments() { return medicaments; }
    public void setMedicaments(List<MedicamentDTO> medicaments) { this.medicaments = medicaments; }
}