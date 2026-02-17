package com.example.ms_ordonnance.DTOs;

import java.util.List;

public class OrdonnanceWithRemboursementDTO {
    private Long id;
    private String date;
    private String numeroSecuriteSocial;
    private Double montantRemboursement;
    private String dateRemboursement;
    private List<MedicamentDTO> medicaments;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getNumeroSecuriteSocial() { return numeroSecuriteSocial; }
    public void setNumeroSecuriteSocial(String numeroSecuriteSocial) { this.numeroSecuriteSocial = numeroSecuriteSocial; }
    public Double getMontantRemboursement() { return montantRemboursement; }
    public void setMontantRemboursement(Double montantRemboursement) { this.montantRemboursement = montantRemboursement; }
    public String getDateRemboursement() { return dateRemboursement; }
    public void setDateRemboursement(String dateRemboursement) { this.dateRemboursement = dateRemboursement; }
    public List<MedicamentDTO> getMedicaments() { return medicaments; }
    public void setMedicaments(List<MedicamentDTO> medicaments) { this.medicaments = medicaments; }
}