package com.chatapp.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.chatapp.entity.Message;
import com.chatapp.service.ChatService;

@Controller
public class ChatMessageController {

    private final ChatService chatService;

    public ChatMessageController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage chatMessage) {

        Message message = new Message(
                chatMessage.getSender(),
                chatMessage.getMessage());

        chatService.saveMessage(message);

        return chatMessage;
    }
}