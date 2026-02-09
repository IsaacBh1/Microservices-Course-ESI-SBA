package com.example.Tp12.Controllers;

import com.example.Tp12.DTOs.CreateVmRequest;
import com.example.Tp12.DTOs.UserDTO;
import com.example.Tp12.Entities.VirtualMachine;
import com.example.Tp12.Entities.Server;
import com.example.Tp12.Entities.User;
import com.example.Tp12.Repositories.ServerRepo;
import com.example.Tp12.Repositories.UserRepo;
import com.example.Tp12.Services.UserService;
import com.example.Tp12.Services.VmService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CloudGraphQLController {
    
    private final VmService vmService;
    private final UserService userService;
    private final UserRepo userRepo;
    private final ServerRepo serverRepo;
    
    @QueryMapping
    public List<VirtualMachine> getVmsByServer(@Argument Long idServer) {
        return vmService.getVmsByServerId(idServer);
    }
    
    @QueryMapping
    public User getUser(@Argument Long id) {
        return userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("User non trouvé"));
    }
    
    @QueryMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
    @QueryMapping
    public List<Server> getAllServers() {
        return serverRepo.findAll();
    }
    
    @MutationMapping
    public VirtualMachine createVm(@Argument CreateVmRequest input) {
        return vmService.createVm(input);
    }
    
    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserDTO input) {
        return userService.updateUser(id, input);
    }
    
    @MutationMapping
    public Boolean deleteVmFromServer(@Argument Long idServer, @Argument Long idVm) {
        vmService.deleteVmFromServer(idServer, idVm);
        return true;
    }
}