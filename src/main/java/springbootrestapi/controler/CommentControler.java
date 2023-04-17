package springbootrestapi.controler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootrestapi.entity.Comment;
import springbootrestapi.payload.CommentDto;
import springbootrestapi.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentControler {
    private CommentService commentService;

    public CommentControler(CommentService commentService) {
        this.commentService = commentService;
    }

@PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable(name = "postId") long postId){
        return  new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);
}

@GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId")long postId){
        return commentService.getCommentsByPostId(postId);
}
}
