// src/main/java/com/example/cloudplatform/dto/CreateVmRequest.java
package com.example.Tp12.DTOs;
import com.example.Tp12.Entities.Configuration;
import lombok.Data;

@Data
public class CreateVmRequest {
    private Long idUser;
    private Long idServer;
    private Long idServerBackup;
    private Configuration configuration;
}