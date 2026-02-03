package com.example.Tp1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.Tp1.Entities.Account;
import com.example.Tp1.Entities.Address;
import com.example.Tp1.Entities.Client;
import com.example.Tp1.Entities.Gender;
import com.example.Tp1.Repositories.AccountRepository;
import com.example.Tp1.Repositories.ClientRepository;

@SpringBootApplication
public class Tp1Application implements CommandLineRunner {

	private final ClientRepository _clientRepository;
	private final AccountRepository _accountRepository;

	public Tp1Application(ClientRepository clientRepository, AccountRepository accountRepository) {
		_clientRepository = clientRepository;
		_accountRepository = accountRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create clients
		Client john = _clientRepository.save(new Client(
			"John Doe", 
			"john.doe@example.com", 
			LocalDate.of(1990, 5, 15), 
			"123-456-7890", 
			Gender.MALE, 
			new Address("123 Main St", "Springfield", "IL", "62704")
		));
		
		Client jane = _clientRepository.save(new Client(
			"Jane Smith", 
			"jane.smith@example.com", 
			LocalDate.of(1992, 8, 25), 
			"987-654-3210", 
			Gender.FEMALE, 
			new Address("456 Elm St", "Springfield", "IL", "62704")
		));

		// Create accounts for the clients
		_accountRepository.save(new Account(
			"johndoe",
			"password123",
			"USER",
			true,
			LocalDateTime.now(),
			john  // Link to John Doe
		));
		
		_accountRepository.save(new Account(
			"janesmith",
			"pass456",
			"ADMIN",
			true,
			LocalDateTime.now(),
			jane  // Link to Jane Smith
		));

		// Print results
		_clientRepository.findAll().forEach(client -> {
			System.out.println("Client ID: " + client.getClientId() + ", Name: " + client.getName());
		});
		
		_accountRepository.findAll().forEach(account -> {
			System.out.println("Account ID: " + account.getId() + ", Login: " + account.getLogin());
		});
}}
