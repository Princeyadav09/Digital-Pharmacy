package com.lattice.assignment.Service.ServiceImpl;

import com.lattice.assignment.Service.PatientService;
import com.lattice.assignment.dao.DoctorRepository;
import com.lattice.assignment.dao.PatientRepository;
import com.lattice.assignment.dto.PatientRequest;
import com.lattice.assignment.entity.Doctor;
import com.lattice.assignment.entity.Patient;
import com.lattice.assignment.enums.City;
import com.lattice.assignment.enums.Speciality;
import com.lattice.assignment.enums.Symptom;
import com.lattice.assignment.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    private final Map<Symptom, Speciality> symptomSpecialtyMap;

    public PatientServiceImpl(Map<Symptom, Speciality> symptomSpecialtyMap) {
        symptomSpecialtyMap.put(Symptom.ARTHRITIS, Speciality.ORTHOPEDIC);
        symptomSpecialtyMap.put(Symptom.BACK_PAIN, Speciality.ORTHOPEDIC);
        symptomSpecialtyMap.put(Symptom.TISSUE_INJURIES, Speciality.ORTHOPEDIC);
        symptomSpecialtyMap.put(Symptom.DYSMENORRHEA, Speciality.GYNECOLOGY);
        symptomSpecialtyMap.put(Symptom.SKIN_INFECTION, Speciality.DERMATOLOGY);
        symptomSpecialtyMap.put(Symptom.SKIN_BURN, Speciality.DERMATOLOGY);
        symptomSpecialtyMap.put(Symptom.EAR_PAIN, Speciality.ENT_SPECIALIST);

        this.symptomSpecialtyMap = symptomSpecialtyMap;
    }


    @Override
    public Patient savePatient(PatientRequest patient) {
        doValidation(patient);
        Patient patient1 = new Patient();
        patient1.setCity(City.from(patient.getCity()));
        patient1.setName(patient.getName());
        patient1.setEmail(patient.getEmail());
        patient1.setPhoneNumber(patient.getPhoneNumber());
        patient1.setSymptom(Symptom.from(patient.getSymptom()));
        return patientRepository.save(patient1);
    }

    @Override
    public Void deletePatient(Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            patientRepository.delete(patient);
        } else {
            throw new BusinessException("Patient with ID " + patientId + " not found",HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public List<Doctor> getDoctors(Long patientId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        Patient patient = patientOpt.get();
        Speciality speciality = symptomSpecialtyMap.get(patient.getSymptom());
        City city = patient.getCity();
        if(doctorRepository.findAllBySpeciality(speciality).isEmpty()){
            throw new BusinessException("There isn’t any doctor present at your location for your symptom", HttpStatus.NOT_FOUND);
        }
        List<Doctor> doctorList = doctorRepository.findAllBySpecialityAndCity(speciality,city);
        if(doctorList.isEmpty()){
            throw new BusinessException("We are still waiting to expand to your location",HttpStatus.NOT_FOUND);
        }

        return doctorList;
    }

    public void doValidation(PatientRequest patient) {
        if (patient.getName() == null || patient.getName().length() < 3) {
            throw new BusinessException("Name should be at least 3 characters", HttpStatus.BAD_REQUEST);
        }

        if (patient.getCity() != null && patient.getCity().length() > 20) {
            throw new BusinessException("City should be at most 20 characters", HttpStatus.BAD_REQUEST);
        }

        if (patient.getEmail() != null && !isValidEmail(patient.getEmail())) {
            throw new BusinessException("Invalid email address", HttpStatus.BAD_REQUEST);
        }

        if (patient.getPhoneNumber() != null && patient.getPhoneNumber().length() < 10) {
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
