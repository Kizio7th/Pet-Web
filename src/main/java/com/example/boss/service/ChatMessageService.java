package com.example.boss.service;

import java.util.List;

import com.example.boss.dto.ChatMessageDto;
import com.example.boss.entity.ChatMessage;

public interface ChatMessageService {
	
	ChatMessage save(ChatMessageDto chatMessageDto);
	Long countNewMessages(Long senderId, Long chatRoomId);
	List<ChatMessage> findChatMessages(Long senderId, Long chatRoomId);
	ChatMessage findById(Long id);
	ChatMessageDto mapToChatMessageDto(ChatMessage chatMessage);
}
