package com.lattice.assignment.Service;

import com.lattice.assignment.dto.PatientRequest;
import com.lattice.assignment.entity.Doctor;
import com.lattice.assignment.entity.Patient;

import java.util.List;

public interface PatientService {
    Patient savePatient(PatientRequest patient);
    Void deletePatient(Long doctorId);
    List<Doctor> getDoctors(Long patientId);

}
