package com.shoes.repository;

import com.shoes.entity.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Integer> {
    List<ChatLog> findByCreatedAtAfter(LocalDateTime date);

    @Query("SELECT COUNT(c) FROM ChatLog c")
    Long countTotalChats();

    @Query(value = "SELECT COUNT(*) FROM chat_log WHERE CAST(created_at AS DATE) = CAST(GETDATE() AS DATE)", nativeQuery = true)
    Long countChatsToday();

    @Query("SELECT COUNT(c) FROM ChatLog c WHERE c.status = 'SUCCESS'")
    Long countSuccessfulChats();

    @Query("SELECT COUNT(DISTINCT c.userEmail) FROM ChatLog c")
    Long countUniqueUsers();

    @Query(value = "SELECT TOP 10 user_email, COUNT(*) as chat_count FROM chat_log GROUP BY user_email ORDER BY chat_count DESC", nativeQuery = true)
    List<Object[]> findTopUsersByChats();

    @Query(value = "SELECT TOP 5 user_message FROM chat_log WHERE user_message IS NOT NULL ORDER BY NEWID()", nativeQuery = true)
    List<String> findSampleQuestions();
}
