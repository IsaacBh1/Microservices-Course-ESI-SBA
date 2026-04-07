package com.example.ms_patient.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.ms_patient.DTOs.RemboursementResponse;

@FeignClient(name = "ms-remboursement")
public interface RemboursementClient {

    @GetMapping("/remboursements/ordonnance/{ordonnanceId}")
    RemboursementResponse getRemboursementByOrdonnance(@PathVariable Long ordonnanceId);
}