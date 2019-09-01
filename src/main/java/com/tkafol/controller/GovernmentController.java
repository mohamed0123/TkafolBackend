package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.Government;
import com.tkafol.repository.GovernmentRepository;
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
@RequestMapping("/governmentApi")
public class GovernmentController {

    @Autowired
    GovernmentRepository governmentRepository;

    @GetMapping("/all")
    public List<Government> getAllGovernments() {
        return governmentRepository.findAll();
    }

    @PostMapping("/create")
    public Government createOrUpdateGovernment(@Valid @RequestBody Government government) {
        return governmentRepository.save(government);
    }

    @GetMapping("/getById/{id}")
    public Government getGovernmentById(@PathVariable(value = "id") Long governmentId) {
        return governmentRepository.findById(governmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Government", "id", governmentId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteGovernment(@PathVariable(value = "id") Long governmentId) {
        Government government = governmentRepository.findById(governmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Government", "id", governmentId));

        governmentRepository.delete(government);

        return ResponseEntity.ok().build();
    }
}
