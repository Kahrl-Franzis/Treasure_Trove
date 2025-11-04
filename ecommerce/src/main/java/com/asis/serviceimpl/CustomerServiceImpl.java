package com.asis.serviceimpl;

import com.asis.entity.Pirate;
import com.asis.service.CustomerService;
import com.asis.repository.PirateDataRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final PirateDataRepository pirateRepository;

    public CustomerServiceImpl(PirateDataRepository pirateRepository) {
        this.pirateRepository = pirateRepository;
    }

    // --- Implementation of CustomerService Interface ---

    @Override
    public Pirate savePirate(Pirate pirate) {
        return pirateRepository.save(pirate);
    }

    @Override
    public Optional<Pirate> findPirateById(Long id) {
        return pirateRepository.findById(id);
    }

    @Override
    public List<Pirate> findAllPirates() {
        return pirateRepository.findAll();
    }

    @Override
    public void deletePirate(Long id) {
        pirateRepository.deleteById(id);
    }

    /**
     * CRITICAL FIX: The method implementation required by the interface.
     * Note: Assuming the interface signature is 'findByUsername(String username)'
     */
    @Override
    public Optional<Pirate> findByUsername(String username) {
        // This calls the case-insensitive repository method.
        return Optional.ofNullable(pirateRepository.findByUsernameIgnoreCase(username));
    }

    // The previous findPirateByUsername method is now named findByUsername to match the interface.\
    @Override
    public Pirate loginPirate(String username, String password) {
        System.out.println("🔍 Attempting login for: " + username);

        // Find the pirate by username (case-insensitive)
        Optional<Pirate> pirateOptional = findByUsername(username.toLowerCase());

        if (pirateOptional.isPresent()) {
            Pirate pirate = pirateOptional.get();
            System.out.println("✅ Pirate found: " + pirate.getUsername());

            // Compare passwords (case-insensitive as per your Angular code)
            if (pirate.getPasswordHash().toLowerCase().equals(password.toLowerCase())) {
                System.out.println("✅ Password matches!");
                return pirate;
            } else {
                System.out.println("❌ Password doesn't match");
            }
        } else {
            System.out.println("❌ Pirate not found");
        }

        return null; // Login failed
    }
}