// src/main/java/com/example/cloudplatform/service/VmService.java
package com.example.Tp12.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.Tp12.DTOs.CreateVmRequest;
import com.example.Tp12.Entities.Server;
import com.example.Tp12.Entities.User;
import com.example.Tp12.Entities.VirtualMachine;
import com.example.Tp12.Repositories.ServerRepo;
import com.example.Tp12.Repositories.UserRepo;
import com.example.Tp12.Repositories.VirtualMachineRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VmService {
    
    private final VirtualMachineRepo vmRepository;
    private final UserRepo UserRepository;
    private final ServerRepo ServerRepository;
    
    public List<VirtualMachine> getVmsByServerId(Long idServer) {
        return vmRepository.findByServerId(idServer);
    }
    
    public VirtualMachine createVm(CreateVmRequest request) {
        User User = UserRepository.findById(request.getIdUser())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Server ServerPrimary = ServerRepository.findById(request.getIdServer())
            .orElseThrow(() -> new RuntimeException("primary Server  not found"));
        
        Server ServerBackup = ServerRepository.findById(request.getIdServerBackup())
            .orElseThrow(() -> new RuntimeException("backup Server  not found"));
        
        VirtualMachine vm = new VirtualMachine();
        vm.setConfiguration(request.getConfiguration());
        vm.setUser(User);
        vm.setServerPrimary(ServerPrimary);
        vm.setServerBackup(ServerBackup);
        
        return vmRepository.save(vm);
    }
    
    public void deleteVmFromServer(Long idServer, Long idVm) {
        VirtualMachine vm = vmRepository.findById(idVm)
            .orElseThrow(() -> new RuntimeException("VM not founde"));
        
        if ((vm.getServerPrimary() != null && vm.getServerPrimary().getId().equals(idServer)) ||
            (vm.getServerBackup() != null && vm.getServerBackup().getId().equals(idServer))) {
            vmRepository.delete(vm);
        } else {
            throw new RuntimeException("VM not founde on this server");
        }
    }
}