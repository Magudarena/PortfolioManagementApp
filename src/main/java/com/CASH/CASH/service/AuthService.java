package com.CASH.CASH.service;

import com.CASH.CASH.model.LoginRequest;
import com.CASH.CASH.model.LoginResponse;
import com.CASH.CASH.model.Owner;
import com.CASH.CASH.repository.OwnerRepository;
import com.CASH.CASH.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private OwnerRepository ownerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponse login(LoginRequest request) {
        Owner owner = ownerRepository.findByEmailAndPassword(request.getEmail(),request.getPassword())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(owner.getEmail());
        return new LoginResponse(token, "Login successful");
    }
}
