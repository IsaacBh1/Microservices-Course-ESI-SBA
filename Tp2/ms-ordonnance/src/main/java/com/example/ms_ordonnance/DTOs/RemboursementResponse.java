package com.example.ms_ordonnance.DTOs;

public class RemboursementResponse {
    private Long id;
    private String date;
    private Double montant;
    private Long ordonnanceId;
    private String numeroSecuriteSocial;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
    public Long getOrdonnanceId() { return ordonnanceId; }
    public void setOrdonnanceId(Long ordonnanceId) { this.ordonnanceId = ordonnanceId; }
    public String getNumeroSecuriteSocial() { return numeroSecuriteSocial; }
    public void setNumeroSecuriteSocial(String numeroSecuriteSocial) { this.numeroSecuriteSocial = numeroSecuriteSocial; }
}