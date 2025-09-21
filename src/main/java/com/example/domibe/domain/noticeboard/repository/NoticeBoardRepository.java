package com.example.domibe.domain.noticeboard.repository;

import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
    Optional<NoticeBoard> findById(Integer id);
}
