package com.example.boss.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.boss.dto.ChatRoomDto;
import com.example.boss.entity.ChatMessage;
import com.example.boss.entity.ChatRoom;
import com.example.boss.entity.User;
import com.example.boss.repository.ChatRoomRepository;
import com.example.boss.repository.UserRepository;
import com.example.boss.service.ChatRoomService;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
	
	private ChatRoomRepository chatRoomRepository;
	private UserRepository userRepository;

	public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
		super();
		this.chatRoomRepository = chatRoomRepository;
		this.userRepository = userRepository;
	}

	@Override
	public ChatRoom findChatRoomByUserAAndUserB(Long userAId, Long userBId) {
		User userA = userRepository.findById(userAId).orElse(null);
		User userB = userRepository.findById(userBId).orElse(null);
		ChatRoom chatRoom = new ChatRoom();
		if(!chatRoomRepository.findByUserAAndUserB(userA, userB).isEmpty()) {
			chatRoom = chatRoomRepository.findByUserAAndUserB(userA, userB).get(0);
		}
		else {
			if(!chatRoomRepository.findByUserAAndUserB(userB, userA).isEmpty()) {
				chatRoom = chatRoomRepository.findByUserAAndUserB(userB, userA).get(0);
			}
			else chatRoom = createChatRoom(userAId, userBId);
		}
		return chatRoom;
	}

	@Override
	public List<ChatRoom> findChatRoomsByUserId(Long id) {
		User user = userRepository.findById(id).orElse(null);
		return chatRoomRepository.findByUserAOrUserB(user,user);
	}
	
	@Override
	public ChatRoom findChatRoomById(Long id) {
		return chatRoomRepository.findById(id).orElse(null);
	}
	
	@Override
	public ChatRoomDto mapToChatRoomDto(ChatRoom chatRoom, Long currentId) {
		ChatRoomDto chatRoomDto = new ChatRoomDto();
		List<ChatMessage> chatMessages = chatRoom.getChatMessages();
		chatRoomDto.setId(chatRoom.getId());
		if(chatRoom.getUserA().getId() == currentId) {
			chatRoomDto.setSenderId(chatRoom.getUserB().getId());
			chatRoomDto.setSenderName(chatRoom.getUserB().getName());
			chatRoomDto.setRecipientId(chatRoom.getUserA().getId());
			chatRoomDto.setRecipientName(chatRoom.getUserA().getName());
		}
		else {
			chatRoomDto.setSenderId(chatRoom.getUserA().getId());
			chatRoomDto.setSenderName(chatRoom.getUserA().getName());
			chatRoomDto.setRecipientId(chatRoom.getUserB().getId());
			chatRoomDto.setRecipientName(chatRoom.getUserB().getName());
		}
		if (!chatMessages.isEmpty()) {
		    ChatMessage lastMessage = chatMessages.get(chatMessages.size() - 1);

		    if (lastMessage.getTimestamp() != null && lastMessage.getDatestamp() != null) {
		    	if(lastMessage.getDatestamp() == Date.valueOf(LocalDate.now()) ) {
		    		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
			        String formattedTime = timeFormat.format(lastMessage.getTimestamp());
			        chatRoomDto.setTime(formattedTime);
		    	}
		    	else chatRoomDto.setTime(lastMessage.getDatestamp().toString());
		    }
		    if (lastMessage.getContent() != null) {
		        chatRoomDto.setPreview(lastMessage.getContent().toString());
		    }
		}

		return chatRoomDto;
	}
	private ChatRoom createChatRoom(Long userA, Long userB){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setUserA(userRepository.findById(userA).orElse(null));
        chatRoom.setUserB(userRepository.findById(userB).orElse(null));
        return chatRoomRepository.save(chatRoom);
    }

	@Override
	public void deleteChatRoom(Long id) {
		chatRoomRepository.deleteById(id);;
	}
	

	
	
	
}
