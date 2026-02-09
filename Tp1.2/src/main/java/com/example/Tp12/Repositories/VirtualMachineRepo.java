package com.example.Tp12.Repositories;

import com.example.Tp12.Entities.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "virtual-machines")
public interface VirtualMachineRepo extends JpaRepository<VirtualMachine, Long> {

    @Query("SELECT mv FROM VirtualMachine mv WHERE mv.serverPrimary.id = :idServer OR mv.serverBackup.id = :idServer")
    List<VirtualMachine> findByServerId(@Param("idServer") Long idServer);
}