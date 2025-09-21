    package com.example.domibe.domain.noticeboard.entity;

    import com.example.domibe.domain.user.User;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.Date;

    @Entity
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class NoticeBoard {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String title;

        @Column(nullable = false)
        private String detail;

        @Column(nullable = false)
        private LocalDateTime createdAt;

        @Column(nullable = false)
        private LocalDateTime updatedAt;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        public void update(String title, String detail,LocalDateTime updatedAt) {
            this.title = title;
            this.detail = detail;
            this.updatedAt = updatedAt;
        }


    }
