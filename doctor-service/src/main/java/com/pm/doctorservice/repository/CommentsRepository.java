package com.pm.doctorservice.repository;

import com.pm.doctorservice.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

  boolean existsCommentByAuthorIdAndCreatedAt(Long authorId, LocalDateTime createdAt);

  @EntityGraph(attributePaths = {"replies"})
  List<Comment> findCommentsByDoctorId(long doctorId);

  @EntityGraph(attributePaths = {"replies"})
  Optional<Comment> findByCommentId(long commentId);

}
