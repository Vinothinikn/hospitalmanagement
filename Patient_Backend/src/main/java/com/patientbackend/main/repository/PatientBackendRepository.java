package com.patientbackend.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patientbackend.main.entity.Patient;

@Repository
public interface PatientBackendRepository extends JpaRepository<Patient, Long>{

}
