package com.example.ms_remboursement.DTOs;

import java.time.LocalDate;

public class RemboursementDetailDTO {
    private Long id;
    private LocalDate dateRemboursement;
    private Double montant;
    private Long ordonnanceId;
    private LocalDate dateOrdonnance;
    private int nombreMedicaments;
    private String numeroSecuriteSocial;
    private String patientNom;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDateRemboursement() {
        return dateRemboursement;
    }
    public void setDateRemboursement(LocalDate dateRemboursement) {
        this.dateRemboursement = dateRemboursement;
    }
    public Double getMontant() {
        return montant;
    }
    public void setMontant(Double montant) {
        this.montant = montant;
    }
    public Long getOrdonnanceId() {
        return ordonnanceId;
    }
    public void setOrdonnanceId(Long ordonnanceId) {
        this.ordonnanceId = ordonnanceId;
    }
    public LocalDate getDateOrdonnance() {
        return dateOrdonnance;
    }
    public void setDateOrdonnance(LocalDate dateOrdonnance) {
        this.dateOrdonnance = dateOrdonnance;
    }
    public int getNombreMedicaments() {
        return nombreMedicaments;
    }
    public void setNombreMedicaments(int nombreMedicaments) {
        this.nombreMedicaments = nombreMedicaments;
    }
    public String getNumeroSecuriteSocial() {
        return numeroSecuriteSocial;
    }
    public void setNumeroSecuriteSocial(String numeroSecuriteSocial) {
        this.numeroSecuriteSocial = numeroSecuriteSocial;
    }
    public String getPatientNom() {
        return patientNom;
    }
    public void setPatientNom(String patientNom) {
        this.patientNom = patientNom;
    }

    // getters / setters
}