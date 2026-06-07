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

    long countByCreatedAtAfter(LocalDateTime date);

    @Query("SELECT COUNT(c) FROM ChatLog c")
    Long countTotalChats();

    @Query("SELECT COUNT(c) FROM ChatLog c WHERE c.status = 'SUCCESS'")
    Long countSuccessfulChats();

    @Query("SELECT COUNT(DISTINCT c.userEmail) FROM ChatLog c")
    Long countUniqueUsers();

    @Query("""
                SELECT c.userEmail, COUNT(c)
                FROM ChatLog c
                WHERE c.userEmail IS NOT NULL
                GROUP BY c.userEmail
                ORDER BY COUNT(c) DESC
            """)
    List<Object[]> findTopUsersByChats();
}