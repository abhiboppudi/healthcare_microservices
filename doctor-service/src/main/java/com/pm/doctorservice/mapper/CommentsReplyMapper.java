package com.pm.doctorservice.mapper;

import com.pm.doctorservice.dto.CommentReplyDTO;
import com.pm.doctorservice.entity.CommentReply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentsReplyMapper {

  CommentReplyDTO toDTO(CommentReply reply);

  CommentReply toEntity(CommentReplyDTO dto);

}
