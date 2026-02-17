package com.example.ms_ordonnance.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.example.ms_ordonnance.entities.Ordonnance;
import com.example.ms_ordonnance.repositories.OrdonnanceRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrdonnanceGraphQLController {

    private final OrdonnanceRepository ordonnanceRepository;

    @QueryMapping
    
    public List<Ordonnance> getOrdonnancesByPatientId(@Argument Long patientId) {
        return ordonnanceRepository.findByPatientId(patientId);
    }
}