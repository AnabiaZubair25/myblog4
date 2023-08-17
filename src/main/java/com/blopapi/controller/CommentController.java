package com.blopapi.controller;

import com.blopapi.payload.CommentDto;
import com.blopapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //http://localhost:8080/api/posts/1/comments
    //http://localhost:8080/api/posts/{postId}/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> findCommentByPostId(@PathVariable(value = "postId") long postId){
       List<CommentDto> dtos= commentService.findCommentByPostId(postId);
       return dtos;
    }
    // //http://localhost:8080/api/posts/1/comments/1
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,@PathVariable(value = "id") long id){
         commentService.deleteCommentById(postId,id);
        return new ResponseEntity<>("Comment is deleted", HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable(value = "postId") long postId,@PathVariable(value = "id")long id){
        CommentDto dto =commentService.getCommentByCommentId(postId,id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentByCommentId(@PathVariable(value = "postId") long postId,
                                                               @PathVariable(value = "id")long id,
                                                               @RequestBody CommentDto commentDto){
        CommentDto dto =commentService.updateCommentByCommentId(postId,id,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}

