package springbootrestapi.controler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootrestapi.payload.CommentDto;
import springbootrestapi.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentControler {
    private CommentService commentService;

    public CommentControler(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId")long postId,
            @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);
    }
}
