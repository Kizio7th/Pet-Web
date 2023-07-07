package com.example.boss.controller;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boss.dto.ChatMessageDto;
import com.example.boss.dto.ChatRoomDto;
import com.example.boss.entity.ChatMessage;
import com.example.boss.entity.ChatRoom;
import com.example.boss.entity.User;
import com.example.boss.service.ChatMessageService;
import com.example.boss.service.ChatRoomService;
import com.example.boss.service.UserService;

@Controller
public class ChatController {

    private ChatMessageService chatMessageService;
    private UserService userService;
    private ChatRoomService chatRoomService;
    private SimpMessagingTemplate messagingTemplate;
    

	

	public ChatController(ChatMessageService chatMessageService, UserService userService,
			ChatRoomService chatRoomService, SimpMessagingTemplate messagingTemplate) {
		super();
		this.chatMessageService = chatMessageService;
		this.userService = userService;
		this.chatRoomService = chatRoomService;
		this.messagingTemplate = messagingTemplate;
	}

	@ModelAttribute("currentUser")
    public User currentUser(Authentication auth) {
        if (auth == null) {
            return new User();
        }
        User currentUser = userService.findUserByEmail(auth.getName());
        return currentUser;
    }

    @ModelAttribute("isAdmin")
    public Boolean isAdmin(Authentication auth) {
        if (auth == null) {
            return false;
        }
        for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
    @GetMapping("/chat")
    public String chat(Model model,Authentication auth) {
    	User currentUser = currentUser(auth);
        List<ChatRoom> chatRooms = chatRoomService.findChatRoomsByUserId(currentUser.getId());
        if(chatRooms.isEmpty()) {
        	return "redirect:/";
        }
        List<ChatRoomDto> chatRoomDtos = new ArrayList<>();
        List<ChatMessage> chatMessages = new ArrayList<>();
        List<ChatMessageDto> chatMessagesDtos = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
        	chatMessages.addAll(chatRoom.getChatMessages());
        	chatRoomDtos.add(chatRoomService.mapToChatRoomDto(chatRoom, currentUser.getId()));
	    }
        for (ChatMessage chatMessage : chatMessages) {
        	chatMessagesDtos.add(chatMessageService.mapToChatMessageDto(chatMessage));
	    }
        model.addAttribute("chatRooms", chatRoomDtos);
        model.addAttribute("chatMessages", chatMessagesDtos);
        return "chat";
    }
    @PostMapping("chat/check/{id}")
    public String checkChatExist(@PathVariable Long id,Authentication auth) {
    	User currentUser = currentUser(auth);
    	chatRoomService.findChatRoomByUserAAndUserB(currentUser.getId(), id);
    	return "redirect:/chat";
    }
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
	public void sendChat(ChatMessageDto chatMessageDto, Authentication auth) {
	    User currentUser = currentUser(auth);
	    chatMessageDto.setSenderId(currentUser.getId());
	    chatMessageDto.setRecipientId(chatMessageDto.getRecipientId());
	    chatMessageDto.setContent(chatMessageDto.getContent());
	    chatMessageDto.setTimestamp(Time.valueOf(LocalTime.now()));
	    chatMessageDto.setDatestamp(Date.valueOf(LocalDate.now()));
	    chatMessageService.save(chatMessageDto);
	
	    messagingTemplate.convertAndSend("/topic/messages", chatMessageDto);
    }
    @GetMapping("/chat/delete/{currentId}/{id}")
    public String deleteChatRoom(@PathVariable("id") Long id,@PathVariable("currentId") Long currentId,Authentication auth){
    	User currentUser = currentUser(auth);
    	if(currentUser.getId() != currentId) {
    		return "redirect:/";
    	}
    	chatRoomService.deleteChatRoom(id);
        return "redirect:/chat";
    }
    
}
