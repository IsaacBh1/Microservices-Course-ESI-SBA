package com.example.ms_ordonnance.DTOs;

public class MedicamentDTO {
    private String nomMedicament;
    private String dosage;
    private Double cout;
    
    public String getNomMedicament() { return nomMedicament; }
    public void setNomMedicament(String nomMedicament) { this.nomMedicament = nomMedicament; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }
}