package com.example.ms_patient.Repositories;

import com.example.ms_patient.Entities.AntecedentMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AntecedentMedicalRepository extends JpaRepository<AntecedentMedical, Long> {
}
