package com.example.Tp12;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Tp12.Entities.User;
import com.example.Tp12.Entities.VirtualMachine;
import com.example.Tp12.Entities.Configuration;
import com.example.Tp12.Entities.Server;
import com.example.Tp12.Repositories.ServerRepo;
import com.example.Tp12.Repositories.UserRepo;
import com.example.Tp12.Repositories.VirtualMachineRepo;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(
            UserRepo userRepo,
            ServerRepo serverRepository,
            VirtualMachineRepo vmRepository) {
        return args -> {
            User user1 = new User();
            user1.setNom("Malki Abdelhamid");
            user1.setEmail("Malki.Abdelhamid@esi-sba.dz");
            userRepo.save(user1);

            User user2 = new User();
            user2.setNom("ishaq boukadeh");
            user2.setEmail("ishaq.boukadeh@gmail.com");
            userRepo.save(user2);

            User user3 = new User();
            user3.setNom("Mohammed Kaddour");
            user3.setEmail("m.kaddour@esi-sba.dz");
            userRepo.save(user3);


            Server server1 = new Server();
            server1.setNom("Server-DC1-01");
            server1.setDatacenter("Datacenter-Alger");
            server1.setConfiguration(new Configuration(32, 128, 2000));
            serverRepository.save(server1);

            Server server2 = new Server();
            server2.setNom("Server-DC2-01");
            server2.setDatacenter("Datacenter-Oran");
            server2.setConfiguration(new Configuration(64, 256, 4000));
            serverRepository.save(server2);

            Server server3 = new Server();
            server3.setNom("Server-DC1-02");
            server3.setDatacenter("Datacenter-Alger");
            server3.setConfiguration(new Configuration(16, 64, 1000));
            serverRepository.save(server3);


            VirtualMachine vm1 = new VirtualMachine();
            vm1.setConfiguration(new Configuration(4, 16, 100));
            vm1.setUser(user1);
            vm1.setServerPrimary(server1);
            vm1.setServerBackup(server2);
            vmRepository.save(vm1);

           VirtualMachine vm2 = new VirtualMachine();
            vm2.setConfiguration(new Configuration(8, 32, 200));
            vm2.setUser(user1);
            vm2.setServerPrimary(server2);
            vm2.setServerBackup(server3);
            vmRepository.save(vm2);

           VirtualMachine vm3 = new VirtualMachine();
            vm3.setConfiguration(new Configuration(2, 8, 50));
            vm3.setUser(user2);
            vm3.setServerPrimary(server3);
            vm3.setServerBackup(server1);
            vmRepository.save(vm3);

           
        };
    }
}