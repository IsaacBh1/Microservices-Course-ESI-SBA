package com.example.Tp12.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    private Integer cpu;
    private Integer ram;
    private Integer disk;
}