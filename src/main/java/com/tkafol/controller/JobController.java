package com.tkafol.controller;

import com.tkafol.exception.ResourceNotFoundException;
import com.tkafol.model.Job;
import com.tkafol.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/jobApi")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @PostMapping("/create")
    public Job createOrUpdateJob(@Valid @RequestBody Job job) {
        return jobRepository.save(job);
    }

    @GetMapping("/getById/{id}")
    public Job getJobById(@PathVariable(value = "id") Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));
    }

   

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable(value = "id") Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", jobId));

        jobRepository.delete(job);

        return ResponseEntity.ok().build();
    }
}
