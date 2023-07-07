package com.example.boss.service.impl;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.boss.dto.PetDto;
import com.example.boss.entity.Pet;
import com.example.boss.entity.User;
import com.example.boss.repository.PetRepository;
import com.example.boss.service.PetService;
import com.example.boss.util.ImageUtil;


@Service
public class PetServiceImpl implements PetService{
	
	private PetRepository petRepository;
	
	public PetServiceImpl(PetRepository petRepository) {
		super();
		this.petRepository = petRepository;
	}

	@Override
	public void savePet(PetDto petDto, MultipartFile petImage, User user) {
		Pet pet = new Pet();
		pet.setName(petDto.getName());
		pet.setSpecies(petDto.getSpecies());
		pet.setBreed(petDto.getBreed());
		pet.setGender(petDto.getGender());
		pet.setWeight(petDto.getWeight());
		pet.setImage(ImageUtil.compressImage(petImage));
		pet.setUser(user);
		
		petRepository.save(pet);
		
	}
	
	@Override
	public void updatePet(PetDto petDto, MultipartFile petImage, Long id) {
		Pet pet = petRepository.findById(id).orElse(null);
		pet.setName(petDto.getName());
		pet.setSpecies(petDto.getSpecies());
		pet.setBreed(petDto.getBreed());
		pet.setGender(petDto.getGender());
		pet.setWeight(petDto.getWeight());
		if (petImage.getSize() != 0) pet.setImage(ImageUtil.compressImage(petImage));
		petRepository.save(pet);
	}
	@Override
	public Pet findPetById(Long id) {
		return petRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<PetDto> findPetsByUserId(Long id) {
		List<Pet> pets = petRepository.findByUserId(id);
		return pets.stream()
				.map((pet) -> mapToPetDto(pet))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PetDto> findPetsByBreed(String breed) {
		List<Pet> pets = petRepository.findByBreed(breed);
		return pets.stream()
				.map((pet) -> mapToPetDto(pet))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<PetDto> findAllPets() {
		List<Pet> pets = petRepository.findAll();
		return pets.stream()
				.map((pet) -> mapToPetDto(pet))
				.collect(Collectors.toList());
	}
	@Override
	public PetDto mapToPetDto(Pet pet) {
		PetDto petDto = new PetDto();
		petDto.setId(pet.getId());
		petDto.setName(pet.getName());
		petDto.setSpecies(pet.getSpecies());
		petDto.setBreed(pet.getBreed());
		petDto.setGender(pet.getGender());
		petDto.setWeight(pet.getWeight());
		petDto.setImage(ImageUtil.decompressImage(pet.getImage()));
		petDto.setOwnerId(pet.getUser().getId());
		return petDto;
	}
	
	@Override
	public void deletePet(Long id) {
		Pet pet = petRepository.findById(id).orElse(null);
		petRepository.deleteById(pet.getId());
		
	}

	

	

}
