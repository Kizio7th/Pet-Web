package com.example.boss.dto;

import java.sql.Date;
import java.sql.Time;


public class ChatMessageDto {
	private Long chatRoomId;
	private Long senderId;
	private Long recipientId;
	private String content;
	private Time timestamp;
	private Date datestamp;
	public ChatMessageDto() {
		super();
	}
	public ChatMessageDto(Long chatRoomId, Long senderId, Long recipientId, String content, Time timestamp,
			Date datestamp) {
		super();
		this.chatRoomId = chatRoomId;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.content = content;
		this.timestamp = timestamp;
		this.datestamp = datestamp;
	}
	
	
	public ChatMessageDto(Long senderId, Long recipientId, String content, Time timestamp, Date datestamp) {
		super();
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.content = content;
		this.timestamp = timestamp;
		this.datestamp = datestamp;
	}
	public Long getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(Long chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
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
	
	
}
