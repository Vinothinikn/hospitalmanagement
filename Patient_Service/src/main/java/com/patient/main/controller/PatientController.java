package com.patient.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.main.dto.Patient;
import com.patient.main.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerPatient(@RequestBody Patient patient){
		return patientService.postRequest(patient);
		
	}

	@GetMapping("/retrieveAll")
	public ResponseEntity<?> retrieveAll(){
		return patientService.getRequestForList();
		
	}
}
