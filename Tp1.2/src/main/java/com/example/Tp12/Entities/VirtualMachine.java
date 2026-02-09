package com.example.Tp12.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VirtualMachine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "cpu", column = @Column(name = "vm_cpu")),
        @AttributeOverride(name = "ram", column = @Column(name = "vm_ram")),
        @AttributeOverride(name = "disk", column = @Column(name = "vm_disk"))
    })
    private Configuration configuration;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_primary_id")
    private Server serverPrimary;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_backup_id")
    private Server serverBackup;
}