package com.example.boss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boss.entity.ChatRoom;
import com.example.boss.entity.User;



public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	List<ChatRoom> findByUserAAndUserB(User userA, User userB);
	List<ChatRoom> findByUserAOrUserB(User userA, User userB);
	
}
