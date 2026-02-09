package com.example.Tp12.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 20)
    private String nom;
    
    @NotBlank
    @Size(max = 20)
    private String datacenter;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "cpu", column = @Column(name = "server_cpu")),
        @AttributeOverride(name = "ram", column = @Column(name = "server_ram")),
        @AttributeOverride(name = "disk", column = @Column(name = "server_disk"))
    })
    private Configuration configuration;
    
    @OneToMany(mappedBy = "serverPrimary", fetch = FetchType.LAZY)
    private List<VirtualMachine> vmsPrimary = new ArrayList<>();
    
    @OneToMany(mappedBy = "serverBackup", fetch = FetchType.LAZY)
    private List<VirtualMachine> vmsBackup = new ArrayList<>();
}