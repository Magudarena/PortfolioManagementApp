package com.CASH.CASH.controller;

import com.CASH.CASH.model.Owner;
import com.CASH.CASH.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping("/login")
    public boolean login(@RequestBody Owner loginRequest) {
        Optional<Owner> owner = ownerRepository.findByEmailAndPassword(
                loginRequest.getEmail(), loginRequest.getPassword()
        );
        return owner.isPresent();
    }
}
