package com.example.boss.service;

import java.util.List;

import com.example.boss.dto.ChatRoomDto;
import com.example.boss.entity.ChatRoom;

public interface ChatRoomService {

	ChatRoom findChatRoomByUserAAndUserB(Long userA, Long userB);
	List<ChatRoom> findChatRoomsByUserId(Long id);
	ChatRoom findChatRoomById(Long id);
	ChatRoomDto mapToChatRoomDto(ChatRoom chatRoom, Long currentId);
	void deleteChatRoom(Long id);
}
