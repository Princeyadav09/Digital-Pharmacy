package com.lattice.assignment.dto;

import lombok.Data;

@Data
public class DoctorRequest {
    private String name;
    private String city;
    private String email;
    private String phoneNumber;
    private String speciality;
}
