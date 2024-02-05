package com.lattice.assignment.dao;

import com.lattice.assignment.entity.Doctor;
import com.lattice.assignment.enums.City;
import com.lattice.assignment.enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    List<Doctor> findAllBySpecialityAndCity(Speciality speciality, City city);
    List<Doctor> findAllBySpeciality(Speciality speciality);
}
