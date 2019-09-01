package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.Gender;
import com.tkafol.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/genderApi")
public class GenderController {

    @Autowired
    GenderRepository genderRepository;

    @GetMapping("/all")
    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    @PostMapping("/create")
    public Gender createOrUpdateGender(@Valid @RequestBody Gender gender) {
        return genderRepository.save(gender);
    }

    @GetMapping("/getById/{id}")
    public Gender getGenderById(@PathVariable(value = "id") Long genderId) {
        return genderRepository.findById(genderId)
                .orElseThrow(() -> new ResourceNotFoundException("Gender", "id", genderId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteGender(@PathVariable(value = "id") Long genderId) {
        Gender gender = genderRepository.findById(genderId)
                .orElseThrow(() -> new ResourceNotFoundException("Gender", "id", genderId));

        genderRepository.delete(gender);

        return ResponseEntity.ok().build();
    }
}
