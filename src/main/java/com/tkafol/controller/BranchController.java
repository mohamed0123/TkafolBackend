package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.Branch;
import com.tkafol.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/branchApi")
public class BranchController {

    @Autowired
    BranchRepository branchRepository;

    @GetMapping("/all")
    public List<Branch> getAllBranchs() {
        return branchRepository.findAll();
    }

    @PostMapping("/create")
    public Branch createOrUpdateBranch(@Valid @RequestBody Branch branch) {
        return branchRepository.save(branch);
    }

    @GetMapping("/getById/{id}")
    public Branch getBranchById(@PathVariable(value = "id") Long branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", branchId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteBranch(@PathVariable(value = "id") Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch", "id", branchId));

        branchRepository.delete(branch);

        return ResponseEntity.ok().build();
    }
}
