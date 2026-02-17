package com.example.Tp12.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerProjectionDTO {
    private String nom;
    private Integer cpu;
    private Integer ram;
    private String datacenter;
}