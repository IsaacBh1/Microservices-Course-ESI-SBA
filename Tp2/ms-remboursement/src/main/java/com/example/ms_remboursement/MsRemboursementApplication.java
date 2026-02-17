package com.example.ms_remboursement;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.ms_remboursement.entities.PatientAssure;
import com.example.ms_remboursement.entities.Remboursement;
import com.example.ms_remboursement.repositories.PatientAssureRepository;
import com.example.ms_remboursement.repositories.RemboursementRepository;
import lombok.RequiredArgsConstructor;
@SpringBootApplication
@RequiredArgsConstructor
public class MsRemboursementApplication implements CommandLineRunner {

	final RemboursementRepository remboursementRepository;
	final PatientAssureRepository patientAssureRepository;
	final RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(MsRemboursementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PatientAssure pa1 = new PatientAssure(null, "1093913", "alice", 1L, new ArrayList<>());
		PatientAssure pa2 = new PatientAssure(null, "1093943", "bob", 2L, new ArrayList<>());
		patientAssureRepository.save(pa1);
		patientAssureRepository.save(pa2);

		Remboursement r1 = new Remboursement(null, LocalDate.of(2024, 1, 20), 35.50, 1L, pa1);
		Remboursement r2 = new Remboursement(null, LocalDate.of(2024, 2, 15), 18.00, 2L, pa2);
		remboursementRepository.save(r1);
		remboursementRepository.save(r2);

		repositoryRestConfiguration.exposeIdsFor(Remboursement.class, PatientAssure.class);
	}
}