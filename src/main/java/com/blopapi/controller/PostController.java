package com.blopapi.controller;

import com.blopapi.payload.PostDto;
import com.blopapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto savedDto = postService.createPost(postDto);
        return  new ResponseEntity<>(savedDto, HttpStatus.CREATED); //status code 201
    }
    //http://localhost:8080/api/posts/1    =query parameter
    @GetMapping("/{id}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto postDto = postService.getpostById(id);

        return new ResponseEntity<>(postDto, HttpStatus.OK); //status code 200
    }

    //http://localhost:8080/api/posts?pageNo&pageSize&sortBy&sortDir
    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title

    @GetMapping
    public List<PostDto> getAllPost(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo  ,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){

        List<PostDto> postDtos= postService.getAllPost(pageNo, pageSize,sortBy,sortDir);
        return postDtos;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id){
        postService.deleteById(id);
        return new ResponseEntity<>("Post is Deleted.", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id,@RequestBody PostDto postDto){
        PostDto dto =postService.updatePost(id,postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
