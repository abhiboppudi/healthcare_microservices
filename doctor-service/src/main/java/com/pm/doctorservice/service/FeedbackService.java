package com.pm.doctorservice.service;

import com.pm.common.exceptions.DuplicateEntityException;
import com.pm.common.exceptions.NoEntityFoundException;
import com.pm.doctorservice.dto.CommentDTO;
import com.pm.doctorservice.dto.CommentReplyDTO;
import com.pm.doctorservice.dto.DoctorFeedbackDTO;
import com.pm.doctorservice.entity.Comment;
import com.pm.doctorservice.entity.CommentReply;
import com.pm.doctorservice.entity.DoctorFeedback;
import com.pm.doctorservice.mapper.CommentsMapper;
import com.pm.doctorservice.mapper.CommentsReplyMapper;
import com.pm.doctorservice.mapper.DoctorFeedbackMapper;
import com.pm.doctorservice.repository.CommentReplyRepository;
import com.pm.doctorservice.repository.CommentsRepository;
import com.pm.doctorservice.repository.DoctorFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pm.doctorservice.constant.errors.ErrorCodes.COMMENT_DUPLICATE;
import static com.pm.doctorservice.constant.errors.ErrorCodes.COMMENT_NOT_FOUND;
import static com.pm.doctorservice.constant.errors.ErrorCodes.FEEDBACK_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class FeedbackService {

  private final CommentsRepository commentsRepository;

  private final CommentsMapper commentsMapper;

  private final DoctorFeedbackRepository feedbackRepository;

  private final DoctorFeedbackMapper feedbackMapper;

  private final CommentReplyRepository replyRepository;

  private final CommentsReplyMapper replyMapper;

  //private static final int negativeThreshold = 3;

  @Value("${spring.application.name}")
  private String serviceName;

  //Comment should not be added to inactive doctor
  @Transactional
  public CommentDTO addComment(Long doctorId, CommentDTO dto) {

    Comment comment = commentsMapper.toComment(dto);

    boolean isDuplicateComment =
        commentsRepository.existsCommentByAuthorIdAndCreatedAt(dto.getAuthorId(), dto.getCreatedAt());

    if (isDuplicateComment) {
      throw new DuplicateEntityException(serviceName, COMMENT_DUPLICATE);
    }

    comment = commentsRepository.save(comment);
    dto = commentsMapper.toCommentDTO(comment);
    addAggregate(dto);

    return dto;
  }

  //Reply should not be added to inactive doctor
  @Transactional
  public CommentReplyDTO addReply(Long commentId, CommentReplyDTO dto) {
    Comment comment = commentsRepository.findByCommentId(commentId)
        .orElseThrow(() -> new NoEntityFoundException(serviceName, COMMENT_NOT_FOUND, dto.getCommentId()));

    CommentReply reply = replyMapper.toEntity(dto);
    reply.setComment(comment);

    reply = replyRepository.save(reply);

    // optional: maintain bidirectional consistency
    comment.getReplies().add(reply);

    return replyMapper.toDTO(reply);
  }


  public List<CommentDTO> getCommentsForDoctor(Long doctorId) {
    return commentsRepository.findCommentsByDoctorId(doctorId)
        .stream()
        .map(commentsMapper::toCommentDTO)
        .toList();
  }


  public DoctorFeedbackDTO getAggregateFeedback(Long doctorId) {
    return feedbackRepository.findByDoctorId(doctorId)
        .map(feedbackMapper::toDoctorFeedbackDTO)
        .orElseThrow(() -> new NoEntityFoundException(serviceName, FEEDBACK_NOT_FOUND, doctorId));
  }


  private void addAggregate(CommentDTO dto) {
    DoctorFeedback feedback = feedbackRepository.findByDoctorId(dto.getDoctorId())
        .map(doctorFeedback -> {
          doctorFeedback.setTotalStars(doctorFeedback.getTotalStars() + dto.getStarCount());
          doctorFeedback.setFeedbackCount(doctorFeedback.getFeedbackCount() + 1);
          return doctorFeedback;
        }).orElseGet(() -> {
          DoctorFeedback doctorFeedback = new DoctorFeedback();
          doctorFeedback.setFeedbackCount(1);
          doctorFeedback.setTotalStars(dto.getStarCount());
          return doctorFeedback;
        });

    feedbackRepository.save(feedback);

  }

}
