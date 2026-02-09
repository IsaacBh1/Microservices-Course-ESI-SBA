package com.example.Tp12.DTOs;

import com.example.Tp12.Entities.Configuration;

import lombok.Data;

@Data
public class VirtualMachineDTO {
    private Long id;
    private Configuration configuration;
    private Long utilisateurId;
    private Long serverPrimaryId;
    private Long serverBackupId;
}