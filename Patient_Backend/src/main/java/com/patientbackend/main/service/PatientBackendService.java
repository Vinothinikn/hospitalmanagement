package com.patientbackend.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientbackend.main.entity.Patient;
import com.patientbackend.main.repository.PatientBackendRepository;

@Service
public class PatientBackendService {

	@Autowired
	PatientBackendRepository repo;
	
	public Patient savePatient(Patient patient) {
		return repo.save(patient);
	}
	
	  public List<Patient> getAllPatients() {
	        return repo.findAll();
	    }
}
