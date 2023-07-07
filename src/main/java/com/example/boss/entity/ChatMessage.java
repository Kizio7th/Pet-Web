package com.example.boss.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_message")
public class ChatMessage {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
    
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "timestamp")
    private Time timestamp;
    
    @Column(name = "datestamp")
    private Date datestamp;
    
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
    
	public ChatMessage() {
		super();
	}

	public ChatMessage(Long id, ChatRoom chatRoom, User sender, String content, Time timestamp, Date datestamp,
			MessageStatus status) {
		super();
		this.id = id;
		this.chatRoom = chatRoom;
		this.sender = sender;
		this.content = content;
		this.timestamp = timestamp;
		this.datestamp = datestamp;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChatRoom getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Time getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Time timestamp) {
		this.timestamp = timestamp;
	}

	public Date getDatestamp() {
		return datestamp;
	}

	public void setDatestamp(Date datestamp) {
		this.datestamp = datestamp;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	
}
