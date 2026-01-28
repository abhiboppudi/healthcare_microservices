package com.pm.doctorservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDTO {
  private Long commentId;

  private Long doctorId;
  
  private Long authorId;

  private String content;

  private int starCount;

  private LocalDateTime createdAt;

  private boolean active;

  private List<CommentReplyDTO> replies;
}
