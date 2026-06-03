package com.shoes.dto;

import java.util.List;

public class ChatStatsResponse {
    private Long totalChats;
    private Long chatsToday;
    private Long successfulChats;
    private Double successRate;
    private Long failedChats;
    private Long uniqueUsers;
    private List<UserChatCount> topUsers;
    private List<String> sampleQuestions;

    public static class UserChatCount {
        public String email;
        public Long count;

        public UserChatCount(String email, Long count) {
            this.email = email;
            this.count = count;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }

    public ChatStatsResponse(Long totalChats, Long chatsToday, Long successfulChats, Long failedChats,
            Long uniqueUsers, List<UserChatCount> topUsers, List<String> sampleQuestions) {
        this.totalChats = totalChats;
        this.chatsToday = chatsToday;
        this.successfulChats = successfulChats;
        this.failedChats = failedChats;
        this.uniqueUsers = uniqueUsers;
        this.topUsers = topUsers;
        this.sampleQuestions = sampleQuestions;
        this.successRate = totalChats > 0 ? (double) successfulChats / totalChats * 100 : 0;
    }

    public Long getTotalChats() {
        return totalChats;
    }

    public void setTotalChats(Long totalChats) {
        this.totalChats = totalChats;
    }

    public Long getChatsToday() {
        return chatsToday;
    }

    public void setChatsToday(Long chatsToday) {
        this.chatsToday = chatsToday;
    }

    public Long getSuccessfulChats() {
        return successfulChats;
    }

    public void setSuccessfulChats(Long successfulChats) {
        this.successfulChats = successfulChats;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public Long getFailedChats() {
        return failedChats;
    }

    public void setFailedChats(Long failedChats) {
        this.failedChats = failedChats;
    }

    public Long getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(Long uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }

    public List<UserChatCount> getTopUsers() {
        return topUsers;
    }

    public void setTopUsers(List<UserChatCount> topUsers) {
        this.topUsers = topUsers;
    }

    public List<String> getSampleQuestions() {
        return sampleQuestions;
    }

    public void setSampleQuestions(List<String> sampleQuestions) {
        this.sampleQuestions = sampleQuestions;
    }
}
