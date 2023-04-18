package springbootrestapi.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

@GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId
    ){
        return  new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId,
            @RequestBody CommentDto commentResponse
    ){
        CommentDto updateComment = commentService.updateComment(postId,commentId,commentResponse);
        return new ResponseEntity<>(updateComment,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "commentId") long commentId
    ){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("comment deleted",HttpStatus.OK);
    }
}
