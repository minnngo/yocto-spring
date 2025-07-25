package com.itsme.yocto_spring.repository;

import com.itsme.yocto_spring.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {

    @Query("""
           SELECT f
           FROM Feedback f
           WHERE f.userId   = :userId
             AND f.type     = :type
             AND f.createdAt BETWEEN :start AND :end
           """)
    List<Feedback> findDailyByUserIdAndType(
            @Param("userId") String userId,
            @Param("type") String type, // "voice" | "image"
            @Param("start") LocalDateTime startOfDay,
            @Param("end") LocalDateTime endOfDay
    );
}

