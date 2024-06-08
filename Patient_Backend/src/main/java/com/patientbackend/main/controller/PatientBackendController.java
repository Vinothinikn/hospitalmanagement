package com.patientbackend.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientbackend.main.dto.StatusCode;
import com.patientbackend.main.entity.Patient;
import com.patientbackend.main.service.PatientBackendService;

@RestController
@RequestMapping("/patientbackend")
public class PatientBackendController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PatientBackendService service;

	@PostMapping("/register")
	public ResponseEntity<?> registerPatient(@RequestBody Patient patient){
		try {
			Patient response = service.savePatient(patient);
			 StatusCode status;

	            if (response != null) {
	                status = new StatusCode(601, "Patient details saved successfully");
	                return new ResponseEntity<>(status, HttpStatus.CREATED);
	            } else {
	                status = new StatusCode(603, "Failed to save patient details");
	                return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	            }
		}catch(Exception e) {
			logger.error("Error in saving the patient details "+e.getMessage());
			StatusCode status = new StatusCode(602, "Saving patient details failed");
			return new ResponseEntity<StatusCode>(status, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/all")
    public ResponseEntity<?> getAllPatientDetails() {
        try {
            List<Patient> patients = service.getAllPatients();
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error in retrieving patient details " + e.getMessage());
            StatusCode status = new StatusCode(604, "Retrieving patient details failed");
            return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
