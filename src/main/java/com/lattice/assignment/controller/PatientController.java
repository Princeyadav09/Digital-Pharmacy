package com.lattice.assignment.controller;

import com.lattice.assignment.Service.PatientService;
import com.lattice.assignment.dto.PatientRequest;
import com.lattice.assignment.entity.Doctor;
import com.lattice.assignment.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody PatientRequest patient){
        return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long patientId){
        return new ResponseEntity<>(patientService.deletePatient(patientId),HttpStatus.OK);
    }

    @GetMapping("/{patientId}/doctors")
    public ResponseEntity<List<Doctor>> getDoctors(@PathVariable Long patientId){
        return new ResponseEntity<>(patientService.getDoctors(patientId),HttpStatus.OK);
    }

}
