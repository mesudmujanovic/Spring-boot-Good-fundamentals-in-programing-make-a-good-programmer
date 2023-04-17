package springbootrestapi.service;

import springbootrestapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
}
