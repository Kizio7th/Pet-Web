package com.example.boss.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "species")
    private String species;
    
    @Column(name = "breed")
    private String breed;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "weight")
    private Float weight;
    
    @Column(name = "image", unique = false, nullable = false, length = 100000)
	private byte[] image;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
	public Pet() {
		super();
	}

	

	public Pet(Long id, String name, String species, String breed, String gender, Float weight, byte[] image,
			User user) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.breed = breed;
		this.gender = gender;
		this.weight = weight;
		this.image = image;
		this.user = user;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
