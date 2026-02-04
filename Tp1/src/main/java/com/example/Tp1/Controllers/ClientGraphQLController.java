package com.example.Tp1.Controllers;

import com.example.Tp1.dto.ClientInput;
import com.example.Tp1.Entities.Client;
import com.example.Tp1.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientGraphQLController {
    
    private final ClientRepository clientRepository;
    
    @QueryMapping
    public List<Client> allClients() {
        return clientRepository.findAll();
    }
    
    @QueryMapping
    public Client clientById(@Argument Long id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }
    
    @QueryMapping
    public Client clientByEmail(@Argument String email) {
        return clientRepository.findByEmail(email);
    }

    @MutationMapping
    public Client createClient(@Argument ClientInput input) {
        Client client = new Client();
        client.setName(input.getName());
        client.setEmail(input.getEmail());
        client.setPhone(input.getPhone());
        
        return clientRepository.save(client);
    }
    
    @MutationMapping
    public Client updateClient(@Argument Long id, @Argument ClientInput input) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        
        client.setName(input.getName());
        client.setEmail(input.getEmail());
        client.setPhone(input.getPhone());
        
        return clientRepository.save(client);
    }
    
    @MutationMapping
    public Boolean deleteClient(@Argument Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
        return true;
    }
}