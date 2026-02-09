package com.example.Tp12.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.Tp12.Entities.Server;
import com.example.Tp12.Projections.ServerProjection;

import java.util.List;

@RepositoryRestResource(path = "servers")
public interface ServerRepo extends JpaRepository<Server, Long> {
    
    @Query("SELECT s.nom as nom, s.configuration.cpu as cpu, " +
           "s.configuration.ram as ram, s.datacenter as datacenter " +
           "FROM Server s")
    List<ServerProjection> getServerProjections();
}