package springbootrestapi.service.impl;

import org.springframework.stereotype.Service;
import springbootrestapi.entity.Comment;
import springbootrestapi.entity.Post;
import springbootrestapi.exception.ResourceNotFoundException;
import springbootrestapi.payload.CommentDto;
import springbootrestapi.repository.CommentRepository;
import springbootrestapi.repository.PostRepository;
import springbootrestapi.service.CommentService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
      Comment comment = mapToEntity(commentDto);
      Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("posts","id",postId));
      comment.setPost(post);
      Comment newComment = commentRepository.save(comment);
      return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return  comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("posts","id",commentId));
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("posts","id",postId));
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentResponse) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("posts","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("posts","id",commentId));
        comment.setEmail(commentResponse.getEmail());
        comment.setName(commentResponse.getName());
        comment.setBody(commentResponse.getBody());
        Comment updateComment = commentRepository.save(comment);
        return mapToDto(updateComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("posts","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("posts","id",commentId));
        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        return commentDto;
    }

    private Comment mapToEntity (CommentDto commentDto){
        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
