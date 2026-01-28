package com.pm.doctorservice.mapper;

import com.pm.doctorservice.dto.CommentDTO;
import com.pm.doctorservice.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

  Comment toComment(CommentDTO dto);

  CommentDTO toCommentDTO(Comment comment);

}
