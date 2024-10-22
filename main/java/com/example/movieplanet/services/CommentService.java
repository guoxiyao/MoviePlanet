package com.example.movieplanet.services;

import com.example.movieplanet.models.Comment;
import com.example.movieplanet.repositories.CommentRepository;
import com.example.movieplanet.responses.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 添加评论
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 根据 ID 获取评论
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    // 获取某个影评下的所有顶级评论（带分页与排序）
    public Page<Comment> getTopLevelCommentsByReviewId(Long reviewId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return commentRepository.findByReview_ReviewIdAndParentCommentIsNull(reviewId, pageable);
    }

    // 获取某条评论的所有回复（带分页与排序）
    public Page<Comment> getRepliesByCommentId(Long parentCommentId, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return commentRepository.findByParentComment_CommentId(parentCommentId, pageable);
    }

    // 递归加载子评论
    public List<Comment> getTopLevelCommentsByReviewIdWithReplies(Long reviewId) {
        List<Comment> topLevelComments = commentRepository.findByReview_ReviewIdAndParentCommentIsNull(reviewId);
        for (Comment comment : topLevelComments) {
            loadChildComments(comment);
        }
        return topLevelComments;
    }

    private void loadChildComments(Comment parentComment) {
        List<Comment> childComments = commentRepository.findByParentComment_CommentId(parentComment.getCommentId());
        for (Comment childComment : childComments) {
            loadChildComments(childComment);
        }
        parentComment.setReplies(childComments);
    }

    // 删除评论
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found with id: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }

    // 获取某条评论的所有回复（仅一层，不分页）
    public List<Comment> getRepliesByCommentId(Long parentCommentId) {
        return commentRepository.findByParentComment_CommentId(parentCommentId);
    }
}
