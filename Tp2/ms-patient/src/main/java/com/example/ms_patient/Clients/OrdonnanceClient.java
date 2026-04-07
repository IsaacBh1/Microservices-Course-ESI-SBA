package com.example.ms_patient.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.ms_patient.DTOs.OrdonnanceResponse;
import java.util.List;

@FeignClient(name = "ms-ordonnance")   // only service name, no url
public interface OrdonnanceClient {

    @GetMapping("/ordonnances/patient/{patientId}")
    List<OrdonnanceResponse> getOrdonnancesByPatient(@PathVariable Long patientId);
}