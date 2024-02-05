package com.lattice.assignment.Service;


import com.lattice.assignment.dto.DoctorRequest;
import com.lattice.assignment.entity.Doctor;

public interface DoctorService {

    Doctor saveDoctor(DoctorRequest doctor);

    Void deleteDoctor(Long doctorId);
}
