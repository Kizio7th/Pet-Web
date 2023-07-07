package com.example.boss.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boss.entity.Pet;


public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByUserId(Long userId);
    List<Pet> findByBreed(String breed);
    Optional<Pet> findById(Long id);
    void deleteById(Long id);
}