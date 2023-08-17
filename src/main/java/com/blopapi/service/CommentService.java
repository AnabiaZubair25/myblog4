package com.blopapi.service;

import com.blopapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> findCommentByPostId(long postId);

    void deleteCommentById(long postId, long id);

    CommentDto getCommentByCommentId(long postId, long id);

    CommentDto updateCommentByCommentId(long postId, long id, CommentDto commentDto);
}
