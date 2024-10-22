package com.example.movieplanet.controllers;

import com.example.movieplanet.models.Comment;
import com.example.movieplanet.responses.ApiResponse;
import com.example.movieplanet.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Comment>> addComment(@RequestBody Comment comment) {
        Comment addedComment = commentService.addComment(comment);
        return ResponseEntity.ok(ApiResponse.success(addedComment, "Comment added successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Comment>> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(c -> ResponseEntity.ok(ApiResponse.success(c)))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error(404, "Comment not found")));
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<ApiResponse<List<Comment>>> getTopLevelCommentsByReviewId(@PathVariable Long reviewId) {
        List<Comment> comments = commentService.getTopLevelCommentsByReviewIdWithReplies(reviewId);
        return ResponseEntity.ok(ApiResponse.success(comments));
    }

    @GetMapping("/replies/{parentId}")
    public ResponseEntity<ApiResponse<List<Comment>>> getRepliesByCommentId(@PathVariable Long parentId) {
        List<Comment> replies = (List<Comment>) commentService.getRepliesByCommentId(parentId);
        return ResponseEntity.ok(ApiResponse.success(replies));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Comment deleted successfully"));
    }
}
