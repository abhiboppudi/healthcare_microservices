package com.pm.doctorservice.controller;

import com.pm.doctorservice.dto.CommentDTO;
import com.pm.doctorservice.dto.CommentReplyDTO;
import com.pm.doctorservice.dto.DoctorFeedbackDTO;
import com.pm.doctorservice.service.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@Tag(name = "doctor-feedback-api-v1", description = "Doctor Feedback API - version 1")
public class FeedbackControllerV1 {

  private final FeedbackService feedbackService;

  public FeedbackControllerV1(FeedbackService feedbackService) {
    this.feedbackService = feedbackService;
  }

  // Patients post comments with star ratings
  @PostMapping("/doctor/{doctorId}/comments")
  public ResponseEntity<CommentDTO> addComment(
      @PathVariable("doctorId") Long doctorId,
      @RequestBody CommentDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(feedbackService.addComment(doctorId, dto));
  }

  // Patients reply to existing comments
  @PostMapping("/comments/{commentId}/replies")
  public ResponseEntity<CommentReplyDTO> addReply(
      @PathVariable("commentId") Long commentId,
      @RequestBody CommentReplyDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(feedbackService.addReply(commentId, dto));
  }

  // Get all comments for a doctor
  @GetMapping("/doctor/{doctorId}/comments")
  public ResponseEntity<List<CommentDTO>> getCommentsForDoctor(@PathVariable("doctorId") Long doctorId) {
    return ResponseEntity.ok(feedbackService.getCommentsForDoctor(doctorId));
  }

  // Get aggregate feedback (PatientFeedback) for a doctor
  @GetMapping("/doctor/{doctorId}/aggregate")
  public ResponseEntity<DoctorFeedbackDTO> getAggregateFeedback(@PathVariable("doctorId") Long doctorId) {
    return ResponseEntity.ok(feedbackService.getAggregateFeedback(doctorId));
  }
}
