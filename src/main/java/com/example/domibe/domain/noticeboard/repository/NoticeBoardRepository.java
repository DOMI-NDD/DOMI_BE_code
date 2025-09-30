package com.example.domibe.domain.noticeboard.repository;

import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import com.example.domibe.domain.noticeboard.specification.NoticeBoardSpecification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Integer> , JpaSpecificationExecutor<NoticeBoard> {
  List<NoticeBoard> findByTitleContainingOrDetailContaining(String title, String detail, Sort sort);
}
