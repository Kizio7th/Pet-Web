package com.example.boss.entity;

import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
    private List<Pet> pets = new ArrayList<>();
    
    @OneToMany(mappedBy = "userA", cascade = CascadeType.ALL )
    private List<ChatRoom> userAChatRoom = new ArrayList<>();
    
    @OneToMany(mappedBy = "userB", cascade = CascadeType.ALL )
    private List<ChatRoom> userBChatRooms = new ArrayList<>();
    
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL )
    private List<ChatMessage> chatMessages = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

	public User() {
		super();
	}

	public User(Long id, String name, String email, String password, List<Pet> pets, List<ChatRoom> userAChatRoom,
			List<ChatRoom> userBChatRooms, List<ChatMessage> chatMessages, List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.pets = pets;
		this.userAChatRoom = userAChatRoom;
		this.userBChatRooms = userBChatRooms;
		this.chatMessages = chatMessages;
		this.roles = roles;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<ChatRoom> getUserAChatRoom() {
		return userAChatRoom;
	}

	public void setUserAChatRoom(List<ChatRoom> userAChatRoom) {
		this.userAChatRoom = userAChatRoom;
	}

	public List<ChatRoom> getUserBChatRooms() {
		return userBChatRooms;
	}

	public void setUserBChatRooms(List<ChatRoom> userBChatRooms) {
		this.userBChatRooms = userBChatRooms;
	}

	public List<ChatMessage> getChatMessages() {
		return chatMessages;
	}

	public void setChatMessages(List<ChatMessage> chatMessages) {
		this.chatMessages = chatMessages;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	
    
}