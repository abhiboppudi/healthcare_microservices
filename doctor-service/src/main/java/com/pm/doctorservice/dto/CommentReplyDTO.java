package com.pm.doctorservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentReplyDTO {
  private Long id;

  private Long commentId;

  private Long replierId;

  private String content;

  private LocalDateTime createdAt;

  private boolean active;
}
