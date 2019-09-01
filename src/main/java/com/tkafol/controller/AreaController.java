package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.Area;
import com.tkafol.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@CrossOrigin
@RestController
@RequestMapping("/areaApi")
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @GetMapping("/all")
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    @PostMapping("/create")
    public Area createOrUpdateArea(@Valid @RequestBody Area area) {
        return areaRepository.save(area);
    }

    @GetMapping("/getById/{id}")
    public Area getAreaById(@PathVariable(value = "id") Long areaId) {
        return areaRepository.findById(areaId)
                .orElseThrow(() -> new ResourceNotFoundException("Area", "id", areaId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteArea(@PathVariable(value = "id") Long areaId) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new ResourceNotFoundException("Area", "id", areaId));

        areaRepository.delete(area);

        return ResponseEntity.ok().build();
    }
}
