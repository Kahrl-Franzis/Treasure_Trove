package com.asis.service;

import com.asis.entity.Pirate;
import java.util.Optional;
import java.util.List;

public interface CustomerService {

    /**
     * Saves a new Pirate or updates an existing one.
     */
    Pirate savePirate(Pirate pirate);

    /**
     * Finds a Pirate by their unique ID.
     */
    Optional<Pirate> findPirateById(Long id);

    /**
     * Returns a list of all Pirates.
     */
    List<Pirate> findAllPirates();

    /**
     * Deletes a Pirate by ID.
     */
    void deletePirate(Long id);

    /**
     * Finds a Pirate by username (useful for login).
     */
    Optional<Pirate> findByUsername(String username);

    // The previous findPirateByUsername method is now named findByUsername to match the interface.\
    Pirate loginPirate(String username, String password);
}
