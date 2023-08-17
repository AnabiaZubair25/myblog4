package com.blopapi.service;

import com.blopapi.payload.PostDto;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    PostDto getpostById(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    void deleteById(long id);

    PostDto updatePost(long id, PostDto postDto);
}
