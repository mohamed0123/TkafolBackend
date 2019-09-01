package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.Case;
import com.tkafol.repository.CaseRepository;
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
@RequestMapping("/caseApi")
public class CaseController {

    @Autowired
    CaseRepository caseRepository;

    @GetMapping("/all")
    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    @PostMapping("/create")
    public Case createOrUpdateCase(@Valid @RequestBody Case case0) {
        return caseRepository.save(case0);
    }

    @GetMapping("/getById/{id}")
    public Case getCaseById(@PathVariable(value = "id") Long caseId) {
        return caseRepository.findById(caseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case", "id", caseId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteCase(@PathVariable(value = "id") Long caseId) {
        Case case0 = caseRepository.findById(caseId)
                .orElseThrow(() -> new ResourceNotFoundException("Case", "id", caseId));

        caseRepository.delete(case0);

        return ResponseEntity.ok().build();
    }
}
