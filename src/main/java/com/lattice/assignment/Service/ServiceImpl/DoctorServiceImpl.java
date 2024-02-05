package com.lattice.assignment.Service.ServiceImpl;

import com.lattice.assignment.Service.DoctorService;
import com.lattice.assignment.dao.DoctorRepository;
import com.lattice.assignment.dto.DoctorRequest;
import com.lattice.assignment.entity.Doctor;
import com.lattice.assignment.enums.City;
import com.lattice.assignment.enums.Speciality;
import com.lattice.assignment.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Doctor saveDoctor(DoctorRequest doctor) {
        doValidation(doctor);
        Doctor doctor1 = new Doctor();
        doctor1.setCity(City.from(doctor.getCity()));
        doctor1.setName(doctor.getName());
        doctor1.setEmail(doctor.getEmail());
        doctor1.setPhoneNumber(doctor.getPhoneNumber());
        doctor1.setSpeciality(Speciality.from(doctor.getSpeciality()));
        return doctorRepository.save(doctor1);
    }

    @Override
    public Void deleteDoctor(Long doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);

        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            doctorRepository.delete(doctor);
        } else {
            throw new BusinessException("Doctor with ID " + doctorId + " not found", HttpStatus.NOT_FOUND);
        }
        return null;
    }

    public void doValidation(DoctorRequest doctor) {
        if (doctor.getName() == null || doctor.getName().length() < 3) {
            throw new BusinessException("Name should be at least 3 characters", HttpStatus.BAD_REQUEST);
        }

        if (doctor.getCity() != null && doctor.getCity().length() > 20) {
            throw new BusinessException("City should be at most 20 characters", HttpStatus.BAD_REQUEST);
        }

        if (doctor.getEmail() != null && !isValidEmail(doctor.getEmail())) {
            throw new BusinessException("Invalid email address", HttpStatus.BAD_REQUEST);
        }

        if (doctor.getPhoneNumber() != null && doctor.getPhoneNumber().length() < 10) {
            throw new BusinessException("Phone number should be at least 10 digits", HttpStatus.BAD_REQUEST);
        }
    }

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }


}
