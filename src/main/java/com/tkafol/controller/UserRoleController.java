package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.UserRole;
import com.tkafol.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/userRoleApi")
public class UserRoleController {

    @Autowired
    UserRoleRepository userRoleRepository;

    @GetMapping("/all")
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @PostMapping("/create")
    public UserRole createOrUpdateUserRole(@Valid @RequestBody UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @GetMapping("/getById/{id}")
    public UserRole getUserRoleById(@PathVariable(value = "id") Long userRoleId) {
        return userRoleRepository.findById(userRoleId)
                .orElseThrow(() -> new ResourceNotFoundException("UserRole", "id", userRoleId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteUserRole(@PathVariable(value = "id") Long userRoleId) {
        UserRole userRole = userRoleRepository.findById(userRoleId)
                .orElseThrow(() -> new ResourceNotFoundException("UserRole", "id", userRoleId));

        userRoleRepository.delete(userRole);

        return ResponseEntity.ok().build();
    }
}
