package springbootrestapi.service;

import springbootrestapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(Long postId,Long commentId);

    CommentDto updateComment(Long postId,long commentId,CommentDto commentResponse);

    void deleteComment(long postId,long commentId);
}
