package com.example.boss.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.boss.dto.ChatMessageDto;
import com.example.boss.entity.ChatMessage;
import com.example.boss.entity.ChatRoom;
import com.example.boss.entity.MessageStatus;
import com.example.boss.repository.ChatMessageRepository;
import com.example.boss.service.ChatMessageService;
import com.example.boss.service.ChatRoomService;
import com.example.boss.service.UserService;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
	
	private ChatMessageRepository chatMessageRepository;
    private ChatRoomService chatRoomService;
    private UserService userService;
    
	public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository, ChatRoomService chatRoomService,
			UserService userService) {
		super();
		this.chatMessageRepository = chatMessageRepository;
		this.chatRoomService = chatRoomService;
		this.userService = userService;
	}

	@Override
	public ChatMessage save(ChatMessageDto chatMessageDto) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setChatRoom(chatRoomService.findChatRoomByUserAAndUserB(chatMessageDto.getSenderId(), chatMessageDto.getRecipientId()));
		chatMessage.setSender(userService.findUserById(chatMessageDto.getSenderId()));
		chatMessage.setContent(chatMessageDto.getContent());
		chatMessage.setTimestamp(chatMessageDto.getTimestamp());
		chatMessage.setDatestamp(chatMessageDto.getDatestamp());
		chatMessage.setStatus(MessageStatus.RECEIVED);
		chatMessageRepository.save(chatMessage);
        return chatMessage;
	}

	@Override
	public Long countNewMessages(Long senderId, Long chatRoomId) {
		ChatRoom chatRoom = chatRoomService.findChatRoomById(chatRoomId);
		Long recipientId;
		if(chatRoom.getUserA().getId() == senderId) {
			recipientId =  chatRoom.getUserB().getId();
		}
		else recipientId =  chatRoom.getUserA().getId();
		return chatMessageRepository.countByChatRoomIdAndSenderIdAndStatus(chatRoom.getId(),recipientId, MessageStatus.RECEIVED);
	}

	@Override
	public List<ChatMessage> findChatMessages(Long senderId, Long chatRoomId) {
		ChatRoom chatRoom = chatRoomService.findChatRoomById(chatRoomId);
		List<ChatMessage> chatMessages = chatMessageRepository.findByChatRoomId(chatRoom.getId());
	    if (!chatMessages.isEmpty()) {
	    	for (ChatMessage chatMessage : chatMessages) {
		        chatMessage.setStatus(MessageStatus.DELIVERED);;
		        chatMessageRepository.save(chatMessage);
		    }
	    }
	    return chatMessages;
	}

	@Override
	public ChatMessage findById(Long id) {
		ChatMessage chatMessage = chatMessageRepository.findById(id).orElse(null);

	    if (chatMessage != null) {
	        chatMessage.setStatus(MessageStatus.DELIVERED); 
	    }
		return chatMessageRepository.save(chatMessage);
	}
	
	
	@Override
	public ChatMessageDto mapToChatMessageDto(ChatMessage chatMessage) {
		ChatMessageDto chatMessageDto = new ChatMessageDto();
		chatMessageDto.setChatRoomId(chatMessage.getChatRoom().getId());
		chatMessageDto.setSenderId(chatMessage.getSender().getId());
		if(chatMessage.getChatRoom().getUserA().getId() != chatMessage.getSender().getId()) {
			chatMessageDto.setRecipientId(chatMessage.getChatRoom().getUserA().getId());
		}
		else chatMessageDto.setRecipientId(chatMessage.getChatRoom().getUserB().getId());
		chatMessageDto.setContent(chatMessage.getContent());
		chatMessageDto.setTimestamp(chatMessage.getTimestamp());
		chatMessageDto.setDatestamp(chatMessage.getDatestamp());
		return chatMessageDto;
	}
	
}
