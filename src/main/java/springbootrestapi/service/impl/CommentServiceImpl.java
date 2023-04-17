package springbootrestapi.service.impl;

import org.springframework.stereotype.Service;
import springbootrestapi.entity.Comment;
import springbootrestapi.entity.Post;
import springbootrestapi.exception.ResourceNotFoundException;
import springbootrestapi.payload.CommentDto;
import springbootrestapi.repository.CommentRepository;
import springbootrestapi.repository.PostRepository;
import springbootrestapi.service.CommentService;

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
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
         commentDto.setId(comment.getId());
         commentDto.setEmail(comment.getEmail());
         commentDto.setBody(comment.getBody());
         commentDto.setName(comment.getName());
         return  commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
