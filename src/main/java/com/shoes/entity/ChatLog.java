package com.shoes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_log")
public class ChatLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chat_log")
    private Integer maChatLog;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_message", columnDefinition = "NVARCHAR(MAX)")
    private String userMessage;

    @Column(name = "bot_reply", columnDefinition = "NVARCHAR(MAX)")
    private String botReply;

    @Column(name = "status")
    private String status; // SUCCESS, FAILED, FALLBACK

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and Setters
    public Integer getMaChatLog() {
        return maChatLog;
    }

    public void setMaChatLog(Integer maChatLog) {
        this.maChatLog = maChatLog;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getBotReply() {
        return botReply;
    }

    public void setBotReply(String botReply) {
        this.botReply = botReply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
