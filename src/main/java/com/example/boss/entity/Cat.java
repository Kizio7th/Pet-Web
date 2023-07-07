package com.example.boss.entity;

public class Cat {
	private String name;
	private String infomation;
	private String link;
	private String description;
	
	public Cat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cat(String name, String infomation, String link, String description) {
		super();
		this.name = name;
		this.infomation = infomation;
		this.link = link;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfomation() {
		return infomation;
	}

	public void setInfomation(String infomation) {
		this.infomation = infomation;
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
