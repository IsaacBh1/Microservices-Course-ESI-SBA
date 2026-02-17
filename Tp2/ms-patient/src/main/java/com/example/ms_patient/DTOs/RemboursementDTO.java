package com.example.ms_patient.DTOs;

public class RemboursementDTO {
    private Double montant;
    private String date;
    
    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}