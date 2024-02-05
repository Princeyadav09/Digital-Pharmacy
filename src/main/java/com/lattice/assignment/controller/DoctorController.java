package com.lattice.assignment.controller;

import com.lattice.assignment.Service.DoctorService;
import com.lattice.assignment.dto.DoctorRequest;
import com.lattice.assignment.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/create")
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorRequest doctor){
        return new ResponseEntity<>(doctorService.saveDoctor(doctor), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long doctorId){
        return new ResponseEntity<>(doctorService.deleteDoctor(doctorId),HttpStatus.OK);
    }

}
