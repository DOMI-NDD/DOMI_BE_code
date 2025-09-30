package com.example.domibe.domain.noticeboard.specification;

import com.example.domibe.domain.noticeboard.entity.NoticeBoard;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NoticeBoardSpecification {

  public static Specification<NoticeBoard> titleContainsWords(String keyword) {
    if(keyword == null || keyword.isEmpty()) {
      return null;
    }

    String[] words = keyword.split("\\s+");

    return (root, query, builder) -> {
      Predicate predicate = builder.conjunction(); // AND 조건
      for (String word : words) {
        predicate = builder.and(predicate, builder.like(root.get("title"), "%" + word + "%"));
      }
      return predicate;
    };
  }

}
