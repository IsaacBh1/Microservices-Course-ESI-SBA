package com.example.Tp1.Repositories;

import com.example.Tp1.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Add custom query methods if needed

    public Client findByEmail(String email);
}