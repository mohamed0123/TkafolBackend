package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.MaterialState;
import com.tkafol.repository.MaterialStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/materialStateApi")
public class MaterialStateController {

    @Autowired
    MaterialStateRepository materialStateRepository;

    @GetMapping("/all")
    public List<MaterialState> getAllMaterialStates() {
        return materialStateRepository.findAll();
    }

    @PostMapping("/create")
    public MaterialState createOrUpdateMaterialState(@Valid @RequestBody MaterialState materialState) {
        return materialStateRepository.save(materialState);
    }

    @GetMapping("/getById/{id}")
    public MaterialState getMaterialStateById(@PathVariable(value = "id") Long materialStateId) {
        return materialStateRepository.findById(materialStateId)
                .orElseThrow(() -> new ResourceNotFoundException("MaterialState", "id", materialStateId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteMaterialState(@PathVariable(value = "id") Long materialStateId) {
        MaterialState materialState = materialStateRepository.findById(materialStateId)
                .orElseThrow(() -> new ResourceNotFoundException("MaterialState", "id", materialStateId));

        materialStateRepository.delete(materialState);

        return ResponseEntity.ok().build();
    }
}
