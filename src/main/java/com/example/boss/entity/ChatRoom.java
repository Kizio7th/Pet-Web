package com.example.boss.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_a_id")
    private User userA;
	
	@ManyToOne
    @JoinColumn(name = "user_b_id")
    private User userB;
	
	@OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL )
    private List<ChatMessage> chatMessages = new ArrayList<>();
	
	public ChatRoom() {
		super();
	}

	public ChatRoom(Long id, User userA, User userB, List<ChatMessage> chatMessages) {
		super();
		this.id = id;
		this.userA = userA;
		this.userB = userB;
		this.chatMessages = chatMessages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserA() {
		return userA;
	}

	public void setUserA(User userA) {
		this.userA = userA;
	}

	public User getUserB() {
		return userB;
	}

	public void setUserB(User userB) {
		this.userB = userB;
	}

	public List<ChatMessage> getChatMessages() {
		return chatMessages;
	}

	public void setChatMessages(List<ChatMessage> chatMessages) {
		this.chatMessages = chatMessages;
	}

	
	
	
	
}
