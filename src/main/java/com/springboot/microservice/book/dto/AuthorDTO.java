package com.springboot.microservice.book.dto;

import com.springboot.microservice.book.entity.Author;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AuthorDTO {

    private Integer id;

    @NotBlank
    private String name;

    @Email
    private String email;


    public static AuthorDTO from(Author author){
        AuthorDTO dto = new AuthorDTO();
        dto.setName(author.getName());
        dto.setEmail(author.getEmail());
        if(author.getId()!=null)
            dto.setId(author.getId());
        return dto;
    }

}
