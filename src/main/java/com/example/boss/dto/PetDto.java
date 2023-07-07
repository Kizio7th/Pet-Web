package com.example.boss.dto;



import jakarta.validation.constraints.NotEmpty;

public class PetDto {
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String species;
	@NotEmpty
	private String breed;
	@NotEmpty
	private String gender;
	private Float weight;
	private String  image;
	private Long ownerId;
	public PetDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PetDto(Long id, @NotEmpty String name, @NotEmpty String species, @NotEmpty String breed,
			@NotEmpty String gender, Float weight, String image, Long ownerId) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.breed = breed;
		this.gender = gender;
		this.weight = weight;
		this.image = image;
		this.ownerId = ownerId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	
	
	
}
