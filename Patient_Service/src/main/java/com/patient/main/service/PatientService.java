package com.patient.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.patient.main.dto.Patient;
import com.patient.main.dto.StatusCode;
import com.patient.main.rest.RestClient;

@Service
public class PatientService {

	@Value("${patient.backend.save}")
	String patientRegisterURL;
	
	@Value("${patient.backend.retrieveall}")
	String patientRetrieveAllURL;
	
	
	 @Autowired
	 RestClient restClient;

    public ResponseEntity<StatusCode> postRequest(Patient patient) {
        StatusCode response = restClient.makePostRequest(patientRegisterURL, patient, StatusCode.class);
        return ResponseEntity.ok(response);
    }
    
    public ResponseEntity<List<Patient>> getRequestForList() {
        return restClient.makeGetRequestForList(patientRetrieveAllURL);
    }
}
