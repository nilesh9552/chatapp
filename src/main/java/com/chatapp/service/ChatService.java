package com.chatapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chatapp.entity.Message;
import com.chatapp.entity.User;
import com.chatapp.repository.MessageRepository;
import com.chatapp.repository.UserRepository;

@Service
public class ChatService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ChatService(UserRepository userRepository,
                       MessageRepository messageRepository) {

        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    // Save User
    public User saveUser(String username) {

        return userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.save(new User(username)));
    }

    // Save Message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // Get All Messages
    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByTimeAsc();
    }

}