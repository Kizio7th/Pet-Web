package com.example.boss.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boss.entity.ChatMessage;
import com.example.boss.entity.MessageStatus;



public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	
	Long countByChatRoomIdAndSenderIdAndStatus(Long chatRoomId, Long senderId, MessageStatus status);
    List<ChatMessage> findByChatRoomId(Long chatRoomId);
    Optional<ChatMessage> findById(Long id);
}
