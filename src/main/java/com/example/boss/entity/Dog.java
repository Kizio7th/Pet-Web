package com.example.boss.entity;

public class Dog {
	private String name;
	private String recommendedFor;
	private String maintenanceLevel;
	private String lifespan;
	private String temperament;
	private String healthRisk;
	private String link;
	private String description;
	
	public Dog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dog(String name, String recommendedFor, String maintenanceLevel, String lifespan, String temperament,
			String healthRisk, String link, String description) {
		super();
		this.name = name;
		this.recommendedFor = recommendedFor;
		this.maintenanceLevel = maintenanceLevel;
		this.lifespan = lifespan;
		this.temperament = temperament;
		this.healthRisk = healthRisk;
		this.link = link;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecommendedFor() {
		return recommendedFor;
	}

	public void setRecommendedFor(String recommendedFor) {
		this.recommendedFor = recommendedFor;
	}

	public String getMaintenanceLevel() {
		return maintenanceLevel;
	}

	public void setMaintenanceLevel(String maintenanceLevel) {
		this.maintenanceLevel = maintenanceLevel;
	}

	public String getLifespan() {
		return lifespan;
	}

	public void setLifespan(String lifespan) {
		this.lifespan = lifespan;
	}

	public String getTemperament() {
		return temperament;
	}

	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}

	public String getHealthRisk() {
		return healthRisk;
	}

	public void setHealthRisk(String healthRisk) {
		this.healthRisk = healthRisk;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
