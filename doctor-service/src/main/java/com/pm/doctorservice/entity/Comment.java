package com.pm.doctorservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @NotNull
  private Long doctorId;

  private Long authorId;

  private String content;

  private int starCount; // 1–5 stars per comment

  private LocalDateTime createdAt;

  private boolean active;

  @OneToMany(mappedBy = "comment")
  private List<CommentReply> replies;

}
