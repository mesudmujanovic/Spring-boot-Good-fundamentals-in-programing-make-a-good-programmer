package springbootrestapi.service;
import springbootrestapi.payload.PostDto;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost(int pageNo,int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost (PostDto postDto,long id);

    void deletePost(long id);
}
