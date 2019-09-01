package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.Diseas;
import com.tkafol.repository.DiseasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@CrossOrigin
@RequestMapping("/diseasApi")
public class DiseasController {

    @Autowired
    DiseasRepository diseasRepository;

    @GetMapping("/all")
    public List<Diseas> getAllDiseass() {
        return diseasRepository.findAll();
    }

    @PostMapping("/create")
    public Diseas createOrUpdateDiseas(@Valid @RequestBody Diseas diseas) {
        return diseasRepository.save(diseas);
    }

    @GetMapping("/getById/{id}")
    public Diseas getDiseasById(@PathVariable(value = "id") Long diseasId) {
        return diseasRepository.findById(diseasId)
                .orElseThrow(() -> new ResourceNotFoundException("Diseas", "id", diseasId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteDiseas(@PathVariable(value = "id") Long diseasId) {
        Diseas diseas = diseasRepository.findById(diseasId)
                .orElseThrow(() -> new ResourceNotFoundException("Diseas", "id", diseasId));

        diseasRepository.delete(diseas);

        return ResponseEntity.ok().build();
    }
}
