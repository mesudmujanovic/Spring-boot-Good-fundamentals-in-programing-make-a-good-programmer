package springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootrestapi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
