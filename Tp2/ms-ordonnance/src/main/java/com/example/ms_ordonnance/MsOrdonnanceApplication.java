package com.example.ms_ordonnance;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ms_ordonnance.entities.Medicament;
import com.example.ms_ordonnance.entities.Ordonnance;
import com.example.ms_ordonnance.repositories.MedicamentRepository;
import com.example.ms_ordonnance.repositories.OrdonnanceRepository;

@SpringBootApplication
public class MsOrdonnanceApplication implements CommandLineRunner {

	final OrdonnanceRepository ordonnanceRepository;
	final MedicamentRepository medicamentRepository;

	public MsOrdonnanceApplication(OrdonnanceRepository ordonnanceRepository, MedicamentRepository medicamentRepository) {
		this.ordonnanceRepository = ordonnanceRepository;
		this.medicamentRepository = medicamentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MsOrdonnanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Ordonnance o1 = new Ordonnance(null, LocalDate.of(2024, 1, 15), 1L, new ArrayList<>());
		Ordonnance o2 = new Ordonnance(null, LocalDate.of(2024, 2, 10), 2L, new ArrayList<>());
		ordonnanceRepository.save(o1);
		ordonnanceRepository.save(o2);

		Medicament m1 = new Medicament(null, "Doliprane 1000mg", 7, "1 comprimé 3x/jour", 15.50, o1);
		Medicament m2 = new Medicament(null, "Amoxicilline 500mg", 10, "1 gélule 2x/jour", 25.00, o1);
		medicamentRepository.save(m1);
		medicamentRepository.save(m2);

		Medicament m3 = new Medicament(null, "Aspirine 100mg", 30, "1 comprimé/jour", 8.00, o2);
		Medicament m4 = new Medicament(null, "Ventoline", 90, "2 bouffées si besoin", 12.50, o2);
		medicamentRepository.save(m3);
		medicamentRepository.save(m4);

	}
}