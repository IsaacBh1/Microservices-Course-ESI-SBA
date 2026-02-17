package com.example.ms_patient;


import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.ms_patient.Entities.AntecedentMedical;
import com.example.ms_patient.Entities.Patient;
import com.example.ms_patient.Repositories.AntecedentMedicalRepository;
import com.example.ms_patient.Repositories.PatientRepository;

@SpringBootApplication
@EnableFeignClients
public class MsPatientApplication implements CommandLineRunner {

	final PatientRepository patientRepository;
	final AntecedentMedicalRepository antecedentMedicalRepository;
	final RepositoryRestConfiguration repositoryRestConfiguration;

	public MsPatientApplication(PatientRepository patientRepository, 
	                            AntecedentMedicalRepository antecedentMedicalRepository,
	                            RepositoryRestConfiguration repositoryRestConfiguration) {
		this.patientRepository = patientRepository;
		this.antecedentMedicalRepository = antecedentMedicalRepository;
		this.repositoryRestConfiguration = repositoryRestConfiguration;
	}

	public static void main(String[] args) {
		SpringApplication.run(MsPatientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	repositoryRestConfiguration.exposeIdsFor(Patient.class);
	Patient p1 = new Patient(null, "said", "1093913", 120.19, new ArrayList<>());
	Patient p2 = new Patient(null, "karim", "1093943", 120.19, new ArrayList<>());
	patientRepository.save(p1);
	patientRepository.save(p2);

	AntecedentMedical a1 = new AntecedentMedical(null, "Asthm", LocalDate.of(2020, 5, 15), p1);
	AntecedentMedical a2 = new AntecedentMedical(null, "Diabete", LocalDate.of(2019, 3, 20), p1);
	antecedentMedicalRepository.save(a1);
	antecedentMedicalRepository.save(a2);

	AntecedentMedical a3 = new AntecedentMedical(null, "grippe", LocalDate.of(2021, 7, 10), p2);
	antecedentMedicalRepository.save(a3);

	repositoryRestConfiguration.exposeIdsFor(Patient.class, AntecedentMedical.class);
	}

}
