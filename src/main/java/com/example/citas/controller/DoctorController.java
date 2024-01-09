package com.example.citas.controller;

import com.example.citas.model.Doctor;
import com.example.citas.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/doctors")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping(value="/getall")
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    //create a doctor
    @PostMapping(value="/create-doctor")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor){
        try{
            Doctor newDoctor =doctorRepository.save(doctor);
            return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
        }catch(Exception e ){
                return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
