package com.example.ms_remboursement.repositories;

import com.example.ms_remboursement.entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    Optional<Remboursement> findByOrdonnanceId(Long ordonnanceId);
}
