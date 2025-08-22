package com.CASH.CASH.service;


import com.CASH.CASH.model.Owner;
import com.CASH.CASH.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public Owner updateOwner(Long id, Owner updatedOwner) {
        return ownerRepository.findById(id)
                .map(owner -> {
                    owner.setName(updatedOwner.getName());
                    owner.setSurname(updatedOwner.getSurname());
                    owner.setEmail(updatedOwner.getEmail());
                    owner.setRegistrationDate(updatedOwner.getRegistrationDate());
                    owner.setBirthDate(updatedOwner.getBirthDate());
                    owner.setPortfolioId(updatedOwner.getPortfolioId());
                    return ownerRepository.save(owner);
                })
                .orElseThrow(() -> new RuntimeException("Owner not found"));
    }

    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}
