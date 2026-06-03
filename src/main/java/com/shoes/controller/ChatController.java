package com.shoes.controller;

import com.shoes.dto.ChatRequest;
import com.shoes.dto.ChatResponse;
import com.shoes.dto.ChatStatsResponse;
import com.shoes.service.ChatService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return chatService.chat(request);
    }

    @GetMapping("/stats")
    public ChatStatsResponse getStats() {
        return chatService.getStats();
    }
}
