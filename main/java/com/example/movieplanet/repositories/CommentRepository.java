package com.example.movieplanet.repositories;

import com.example.movieplanet.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 分页查询影评下的顶级评论（没有父评论的评论）
    Page<Comment> findByReview_ReviewIdAndParentCommentIsNull(Long reviewId, Pageable pageable);

    // 分页查询某条评论的子评论
    Page<Comment> findByParentComment_CommentId(Long parentCommentId, Pageable pageable);

    // 不分页查询影评下的顶级评论
    List<Comment> findByReview_ReviewIdAndParentCommentIsNull(Long reviewId);

    // 不分页查询某条评论的子评论
    List<Comment> findByParentComment_CommentId(Long parentCommentId);
}
