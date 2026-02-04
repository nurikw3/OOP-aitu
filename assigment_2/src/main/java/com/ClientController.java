package com;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> clients = DBHelper.getAllClients();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error loading clients: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }
            
            Client client = DBHelper.getClientById(id);
            if (client != null) {
                return ResponseEntity.ok(client);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error loading client: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            // Validation
            if (client.getId() <= 0) {
                return ResponseEntity.badRequest().body("Invalid client ID");
            }
            if (client.getName() == null || client.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (!client.getEmail().contains("@")) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            if (client.getCompany() == null || client.getCompany().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Company is required");
            }
            
            boolean success = DBHelper.saveClient(client);
            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Client created successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create client");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating client: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable int id, @RequestBody Client client) {
        try {
            // Validation
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }
            if (client.getName() == null || client.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name is required");
            }
            if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required");
            }
            if (!client.getEmail().contains("@")) {
                return ResponseEntity.badRequest().body("Invalid email format");
            }
            if (client.getCompany() == null || client.getCompany().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Company is required");
            }
            
            // Check if client exists
            Client existing = DBHelper.getClientById(id);
            if (existing == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Client not found with ID: " + id);
            }
            
            boolean success = DBHelper.updateClient(client);
            if (success) {
                return ResponseEntity.ok("Client updated successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update client");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating client: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid ID");
            }
            
            // Check if client exists
            Client existing = DBHelper.getClientById(id);
            if (existing == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Client not found with ID: " + id);
            }
            
            boolean success = DBHelper.deleteClient(id);
            if (success) {
                return ResponseEntity.ok("Client deleted successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete client");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting client: " + e.getMessage());
        }
    }
}
