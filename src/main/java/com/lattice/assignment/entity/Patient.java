package com.lattice.assignment.entity;

import com.lattice.assignment.enums.City;
import com.lattice.assignment.enums.Symptom;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private City city;
    private String email;
    private String phoneNumber;
    private Symptom symptom;
}
