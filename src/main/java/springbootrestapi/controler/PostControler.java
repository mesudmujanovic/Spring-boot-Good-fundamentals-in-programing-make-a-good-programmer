package springbootrestapi.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootrestapi.entity.Post;
import springbootrestapi.payload.PostDto;
import springbootrestapi.service.PostService;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostControler {

    private PostService postService;

    public PostControler(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    };

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id")long id){
        PostDto postResponse = postService.updatePost(postDto,id);
        return new  ResponseEntity<>(postResponse,HttpStatus.OK);
    }
}
