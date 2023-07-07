package com.example.boss.dto;

public class ChatRoomDto {
	private Long id;
	private Long senderId;
	private String senderName;
	private Long recipientId;
	private String recipientName;
	private String time;
	private String preview;
	
	
	public ChatRoomDto() {
		super();
	}


	public ChatRoomDto(Long id, Long senderId, String senderName, Long recipientId, String recipientName, String time,
			String preview) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.senderName = senderName;
		this.recipientId = recipientId;
		this.recipientName = recipientName;
		this.time = time;
		this.preview = preview;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getSenderId() {
		return senderId;
	}


	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}


	public String getSenderName() {
		return senderName;
	}


	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	public Long getRecipientId() {
		return recipientId;
	}


	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}


	public String getRecipientName() {
		return recipientName;
	}


	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getPreview() {
		return preview;
	}


	public void setPreview(String preview) {
		this.preview = preview;
	}

	
	


	
	
	
}