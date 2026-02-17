package com.example.ms_patient.DTOs;

import java.util.List;

public class PatientWithMedicamentsDTO {
   private Long id;
    private String nom;
    private String numeroSecuriteSocial;
    private Double plafondRemboursement;
    private List<MedicamentDTO> medicaments;
    public List<MedicamentDTO> getMedicaments() { return medicaments; }
    public void setMedicaments(List<MedicamentDTO> medicaments) { this.medicaments = medicaments; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getNumeroSecuriteSocial() { return numeroSecuriteSocial; }
    public void setNumeroSecuriteSocial(String numeroSecuriteSocial) { 
        this.numeroSecuriteSocial = numeroSecuriteSocial; 
    }
    public Double getPlafondRemboursement() { return plafondRemboursement; }
    public void setPlafondRemboursement(Double plafondRemboursement) { 
        this.plafondRemboursement = plafondRemboursement; 
    }
}