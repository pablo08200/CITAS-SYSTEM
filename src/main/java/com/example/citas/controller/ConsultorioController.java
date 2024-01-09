package com.example.citas.controller;

import com.example.citas.model.Consultorio;
import com.example.citas.model.Doctor;
import com.example.citas.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/consultorio")
public class ConsultorioController {
    @Autowired
    private ConsultorioRepository consultorioRepository;

    @GetMapping(value="/getConsultorios")
    public List<Consultorio> getAllConsultorios(){
        return consultorioRepository.findAll();
    }

    @PostMapping(value="/create-consultorio")
    public ResponseEntity<Consultorio> createDoctor(@RequestBody Consultorio consultorio){
        try{
            Consultorio newConsultorio1 =consultorioRepository.save(consultorio);
            return new ResponseEntity<>(newConsultorio1, HttpStatus.CREATED);
        }catch(Exception e ){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
