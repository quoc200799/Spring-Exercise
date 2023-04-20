package com.example.jwt.service;

import com.example.jwt.entity.Comment;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repository.CommentRepository;
import com.example.jwt.request.UpdateCommentRequest;
import com.example.jwt.request.UpsertUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    public Page<Comment> getAllComment(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Comment> pagedResult = commentRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }

    public String updateCommentById(Integer id, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("not found id");
        });
       comment.setContent(request.getContent());
        commentRepository.save(comment);
        return "Cập nhật thành công!";
    }
    public String deleteCommentById(Integer id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("not found id");
        });
        commentRepository.delete(comment);
        return "Xóa thành công!";
    }


}
