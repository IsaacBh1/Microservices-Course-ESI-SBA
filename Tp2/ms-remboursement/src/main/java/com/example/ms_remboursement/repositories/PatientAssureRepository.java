package com.example.ms_remboursement.repositories;

import com.example.ms_remboursement.entities.PatientAssure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAssureRepository extends JpaRepository<PatientAssure, Long> {
}
