package com.example.ms_ordonnance.DTOs;

public class MedicamentResponse {
    private String nom;
    private Double cout;
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Double getCout() { return cout; }
    public void setCout(Double cout) { this.cout = cout; }
}