package com.example.ms_remboursement.repositories;

import com.example.ms_remboursement.entities.PatientAssure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PatientAssureRepository extends JpaRepository<PatientAssure, Long> {
}
