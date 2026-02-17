package com.example.ms_patient.Clients;
import java.util.List;

import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

import com.example.ms_patient.DTOs.OrdonnanceDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdonnanceGraphQLClient {

    private final HttpGraphQlClient ordonnanceGraphQlClient;

    private static final String GET_ORDONNANCES_BY_PATIENT_ID = """
            query GetOrdonnancesByPatientId($patientId: ID!) {
                getOrdonnancesByPatientId(patientId: $patientId) {
                    id
                    date
                    patientId
                    medicaments {
                        id
                        nomMedicament
                        duree
                        dosage
                        cout
                    }
                }
            }
            """;

    public List<OrdonnanceDTO> getOrdonnancesByPatientId(Long patientId) {
        return ordonnanceGraphQlClient.document(GET_ORDONNANCES_BY_PATIENT_ID)
                .variable("patientId", patientId)
                .retrieve("getOrdonnancesByPatientId")
                .toEntityList(OrdonnanceDTO.class)
                .block();
    }
}