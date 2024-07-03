package Araiguma.SpyCat.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Araiguma.SpyCat.Models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByUser_id(long id);
}
