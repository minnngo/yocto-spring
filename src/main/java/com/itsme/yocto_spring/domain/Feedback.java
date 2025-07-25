package com.itsme.yocto_spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @Column(name = "feedback_id", length = 36, nullable = false)
    private String feedbackId;

    // 음성/이미지 중 하나만 채워지는 구조 (DB CHECK 제약과 일치)
    @Column(name = "voice_id", length = 36)
    private String voiceId;

    @Column(name = "image_id", length = 36)
    private String imageId;

    @Column(name = "user_id", length = 36, nullable = false)
    private String userId;

    @Column(name = "transcribed_text", columnDefinition = "TEXT")
    private String transcribedText;

    @Column(name = "corrected_text", columnDefinition = "TEXT")
    private String correctedText;

    @Column(name = "is_valid", nullable = false)
    private Boolean isValid;

    // "voice" | "image"
    @Column(name = "type", length = 16, nullable = false)
    private String type;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
