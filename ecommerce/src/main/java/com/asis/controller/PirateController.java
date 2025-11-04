package com.asis.controller;

import com.asis.entity.Pirate;
import com.asis.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pirates")
public class PirateController {

    private final CustomerService customerService;

    public PirateController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * GET /api/pirates - Retrieves the entire list of Pirates.
     */
    @GetMapping
    public ResponseEntity<List<Pirate>> getAllPirates() {
        List<Pirate> pirates = customerService.findAllPirates();
        return new ResponseEntity<>(pirates, HttpStatus.OK);
    }

    /**
     * POST /api/pirates - Registers a new Pirate (Customer)
     */
    @PostMapping
    public ResponseEntity<Pirate> createPirate(@RequestBody Pirate pirate) {
        Pirate newPirate = customerService.savePirate(pirate);
        return new ResponseEntity<>(newPirate, HttpStatus.CREATED);
    }

    /**
     * POST /api/pirates/login - Authenticates a pirate by username and password
     */
    @PostMapping("/login")
    public ResponseEntity<Pirate> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("🔥 Login attempt for username: " + loginRequest.getUsername());

        Pirate pirate = customerService.loginPirate(
                loginRequest.getUsername().toLowerCase(),
                loginRequest.getPassword().toLowerCase()
        );

        if (pirate != null) {
            System.out.println("✅ Login successful for: " + pirate.getUsername());
            return new ResponseEntity<>(pirate, HttpStatus.OK);
        } else {
            System.out.println("❌ Login failed - invalid credentials");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * GET /api/pirates/{id} - Finds a Pirate by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pirate> getPirateById(@PathVariable Long id) {
        Pirate pirate = customerService.findPirateById(id)
                .orElseThrow(() -> new RuntimeException("Pirate not found!"));
        return new ResponseEntity<>(pirate, HttpStatus.OK);
    }

    /**
     * DELETE /api/pirates/{id} - Deletes a Pirate
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePirate(@PathVariable Long id) {
        customerService.deletePirate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Simple DTO class for login credentials
     */
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}