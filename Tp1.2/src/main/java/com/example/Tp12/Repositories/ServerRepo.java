package com.example.Tp12.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.Tp12.Entities.Server;
import com.example.Tp12.Entities.ServerProjectionDTO;

import java.util.List;

@RepositoryRestResource(path = "servers")
public interface ServerRepo extends JpaRepository<Server, Long> {
    
    @Query("SELECT new com.example.Tp12.Entities.ServerProjectionDTO(" +
        "s.nom, s.configuration.cpu, s.configuration.ram, s.datacenter) " +
        "FROM Server s WHERE s.id = :id")
    List<ServerProjectionDTO> getServerProjections(long id);
}