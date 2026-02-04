package com;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freelancers")
public class FreelancerController {

    @GetMapping
    public ResponseEntity<?> getAllFreelancers(@RequestParam(required = false) String sortBy) {
        try {
            List<Freelancer> freelancers = DBHelper.getAllFreelancers();

            // Apply sorting if requested
            if (sortBy != null) {
                if (sortBy.equals("rate")) {
                    sortFreelancersByRate(freelancers);
                } else if (sortBy.equals("rating")) {
                    sortFreelancersByRating(freelancers);
                }
            }

            return ResponseEntity.ok(freelancers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error loading freelancers: " + e.getMessage());
        }
    }

    // Bubble sort for freelancers by hourly rate
    private void sortFreelancersByRate(List<Freelancer> freelancers) {
        for (int i = 0; i < freelancers.size(); i++) {
            for (int j = 0; j < freelancers.size() - i - 1; j++) {
                if (freelancers.get(j).getHourlyRate() > freelancers.get(j + 1).getHourlyRate()) {
                    Freelancer temp = freelancers.get(j);
                    freelancers.set(j, freelancers.get(j + 1));
                    freelancers.set(j + 1, temp);
                }
            }
        }
    }

    // Bubble sort for freelancers by rating
    private void sortFreelancersByRating(List<Freelancer> freelancers) {
        for (int i = 0; i < freelancers.size(); i++) {
            for (int j = 0; j < freelancers.size() - i - 1; j++) {
                if (freelancers.get(j).getRating() > freelancers.get(j + 1).getRating()) {
                    Freelancer temp = freelancers.get(j);
                    freelancers.set(j, freelancers.get(j + 1));
                    freelancers.set(j + 1, temp);
                }
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFreelancerById(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }

            Freelancer freelancer = DBHelper.getFreelancerById(id);
            if (freelancer != null) {
                return ResponseEntity.ok(freelancer);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Freelancer not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error loading freelancer: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createFreelancer(@RequestBody Freelancer freelancer) {
        try {
            // Validation
            if (freelancer.getId() <= 0) {
                return ResponseEntity.badRequest().body("Invalid freelancer ID");
            }
            if (freelancer.getName() == null || freelancer.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (freelancer.getEmail() == null || freelancer.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (!freelancer.getEmail().contains("@")) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            if (freelancer.getHourlyRate() <= 0) {
                return ResponseEntity.badRequest().body("Hourly rate must be positive");
            }
            if (freelancer.getRating() < 0 || freelancer.getRating() > 5) {
                return ResponseEntity.badRequest().body("Rating must be between 0 and 5");
            }

            boolean success = DBHelper.saveFreelancer(freelancer);
            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Freelancer created successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create freelancer");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating freelancer: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFreelancer(@PathVariable int id, @RequestBody Freelancer freelancer) {
        try {
            // Validation
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }
            if (freelancer.getName() == null || freelancer.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (freelancer.getEmail() == null || freelancer.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (!freelancer.getEmail().contains("@")) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            if (freelancer.getHourlyRate() <= 0) {
                return ResponseEntity.badRequest().body("Hourly rate must be positive");
            }
            if (freelancer.getRating() < 0 || freelancer.getRating() > 5) {
                return ResponseEntity.badRequest().body("Rating must be between 0 and 5");
            }

            // Check if freelancer exists
            Freelancer existing = DBHelper.getFreelancerById(id);
            if (existing == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Freelancer not found with ID: " + id);
            }

            boolean success = DBHelper.updateFreelancer(freelancer);
            if (success) {
                return ResponseEntity.ok("Freelancer updated successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update freelancer");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating freelancer: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFreelancer(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }

            // Check if freelancer exists
            Freelancer existing = DBHelper.getFreelancerById(id);
            if (existing == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Freelancer not found with ID: " + id);
            }

            boolean success = DBHelper.deleteFreelancer(id);
            if (success) {
                return ResponseEntity.ok("Freelancer deleted successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete freelancer");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting freelancer: " + e.getMessage());
        }
    }
}