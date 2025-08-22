package com.CASH.CASH.service;

import com.CASH.CASH.model.LoginRequest;
import com.CASH.CASH.model.LoginResponse;
import com.CASH.CASH.model.Owner;
import com.CASH.CASH.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private OwnerRepository ownerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponse login(LoginRequest request) {
        Owner owner = ownerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika"));

/*        if (!passwordEncoder.matches(request.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Nieprawidłowe hasło");
        }*/

        return new LoginResponse("FAKE_TOKEN", "Zalogowano pomyślnie");
    }
}
