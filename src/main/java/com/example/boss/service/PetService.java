package com.example.boss.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.boss.dto.PetDto;
import com.example.boss.entity.Pet;
import com.example.boss.entity.User;

public interface PetService {
	void savePet(PetDto petDto, MultipartFile petImage, User user) ;
	Pet findPetById(Long id);
	List<PetDto> findPetsByBreed(String breed);
	List<PetDto> findPetsByUserId(Long id);
	List<PetDto> findAllPets();
	void updatePet(PetDto petDto, MultipartFile petImage, Long id);
	PetDto mapToPetDto(Pet pet);
	void deletePet(Long id);
}
