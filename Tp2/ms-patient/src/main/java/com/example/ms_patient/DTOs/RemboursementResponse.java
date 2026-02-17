package com.example.ms_patient.DTOs;

public class RemboursementResponse {
    private Long id;
    private String date;
    private Double montant;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
}