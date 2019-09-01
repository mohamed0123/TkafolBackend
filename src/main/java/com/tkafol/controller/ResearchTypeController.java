package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.ResearchType;
import com.tkafol.repository.ResearchTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/researchTypeApi")
public class ResearchTypeController {

    @Autowired
    ResearchTypeRepository researchTypeRepository;

    @GetMapping("/all")
    public List<ResearchType> getAllResearchTypes() {
        return researchTypeRepository.findAll();
    }

    @PostMapping("/create")
    public ResearchType createOrUpdateResearchType(@Valid @RequestBody ResearchType researchType) {
        return researchTypeRepository.save(researchType);
    }

    @GetMapping("/getById/{id}")
    public ResearchType getResearchTypeById(@PathVariable(value = "id") Long researchTypeId) {
        return researchTypeRepository.findById(researchTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ResearchType", "id", researchTypeId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteResearchType(@PathVariable(value = "id") Long researchTypeId) {
        ResearchType researchType = researchTypeRepository.findById(researchTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("ResearchType", "id", researchTypeId));

        researchTypeRepository.delete(researchType);

        return ResponseEntity.ok().build();
    }
}
