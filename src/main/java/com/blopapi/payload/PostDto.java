package com.blopapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private  long id;
    @NotEmpty
    @Size(min = 2,message = "Title should be more than 2 char")
    private String title;
    @NotEmpty
    @Size(min = 2,message = "description should be more than 2 char")
    private String description;
    @NotEmpty(message = "Content must not be empty")
    private String content;

}
