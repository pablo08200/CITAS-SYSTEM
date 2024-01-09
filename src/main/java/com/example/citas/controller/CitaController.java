package com.example.citas.controller;


import com.example.citas.model.Cita;
import com.example.citas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/cita")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping(value="getAllCitas")
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @PostMapping(value="create-cita")
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        try {
            Cita newCita = citaRepository.save(cita);
            return new ResponseEntity<>(newCita, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modificar-cita/{id_cita}")
    public ResponseEntity<Cita> updateCita(@PathVariable Long id_cita, @RequestBody Cita updatedCita) {
        Optional<Cita> citaData = citaRepository.findById(id_cita);

        if (citaData.isPresent()) {
            Cita existingCita = citaData.get();
            existingCita.setConsultorio(updatedCita.getConsultorio());
            existingCita.setFecha(updatedCita.getFecha());
            existingCita.setHora(updatedCita.getHora());


            Cita updatedCitaEntity = citaRepository.save(existingCita);
            return new ResponseEntity<>(updatedCitaEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-cita/{id_cita}")
    public ResponseEntity<HttpStatus> deleteCita(@PathVariable Long id_cita) {
        try {
            citaRepository.deleteById(id_cita);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
