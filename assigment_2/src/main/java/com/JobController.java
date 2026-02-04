package com;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @GetMapping
    public ResponseEntity<?> getAllJobs() {
        try {
            List<Job> jobs = DBHelper.getAllJobs();
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error loading jobs: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }
            
            Job job = DBHelper.getJobById(id);
            if (job != null) {
                return ResponseEntity.ok(job);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Job not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error loading job: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        try {
            // Validation
            if (job.getJobId() <= 0) {
                return ResponseEntity.badRequest().body("Invalid job ID");
            }
            if (job.getTitle() == null || job.getTitle().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Title is required");
            }
            if (job.getBudget() <= 0) {
                return ResponseEntity.badRequest().body("Budget must be positive");
            }
            if (job.getDuration() == null || job.getDuration().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Duration is required");
            }
            
            boolean success = DBHelper.saveJob(job);
            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Job created successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create job");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating job: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable int id, @RequestBody Job job) {
        try {
            // Validation
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }
            if (job.getTitle() == null || job.getTitle().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Title is required");
            }
            if (job.getBudget() <= 0) {
                return ResponseEntity.badRequest().body("Budget must be positive");
            }
            
            // Check if job exists
            Job existing = DBHelper.getJobById(id);
            if (existing == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Job not found with ID: " + id);
            }
            
            boolean success = DBHelper.updateJob(job);
            if (success) {
                return ResponseEntity.ok("Job updated successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update job");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating job: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }
            
            // Check if job exists
            Job existing = DBHelper.getJobById(id);
            if (existing == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Job not found with ID: " + id);
            }
            
            boolean success = DBHelper.deleteJob(id);
            if (success) {
                return ResponseEntity.ok("Job deleted successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete job");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting job: " + e.getMessage());
        }
    }
}
