package com.example.jwt.controller;

import com.example.jwt.entity.Comment;
import com.example.jwt.entity.User;
import com.example.jwt.request.UpdateCommentRequest;
import com.example.jwt.request.UpsertUserRequest;
import com.example.jwt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("")
    public ResponseEntity<Page<Comment>> getAllComments(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize
    ) {
        Page<Comment> list = commentService.getAllComment(page, pageSize);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<?> UpdateComment(@PathVariable Integer id,@PathVariable UpdateCommentRequest request) {
        String blog = commentService.updateCommentById(id,request);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        String blog = commentService.deleteCommentById(id);
        return new ResponseEntity<>(blog, new HttpHeaders(), HttpStatus.OK);
    }
}
