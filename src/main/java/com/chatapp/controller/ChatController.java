package com.chatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chatapp.service.ChatService;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(String username, Model model) {

        chatService.saveUser(username);

        model.addAttribute("username", username);
        model.addAttribute("messages", chatService.getAllMessages());

        return "chat";
    }

}