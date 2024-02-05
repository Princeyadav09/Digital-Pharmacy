package com.lattice.assignment.dto;


import lombok.Data;

@Data
public class PatientRequest {
    private String name;
    private String city;
    private String email;
    private String phoneNumber;
    private String symptom;
}
