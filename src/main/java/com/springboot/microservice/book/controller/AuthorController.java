package com.springboot.microservice.book.controller;

import com.springboot.microservice.book.dto.AuthorDTO;
import com.springboot.microservice.book.entity.Author;
import com.springboot.microservice.book.service.AuthorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorServices authorServices;


    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAuthors(){
        List<AuthorDTO> authorsDtoList=new ArrayList<>();
        List<Author> authors=authorServices.getAuthors();
        for (Author author:authors) {
            AuthorDTO theAuthor=AuthorDTO.from(author);
            authorsDtoList.add(theAuthor);
        }
        return ResponseEntity.ok().body(authorsDtoList);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("authorId") Integer authorID){
        Author author=authorServices.getAuthorById(authorID);
        AuthorDTO authorDto= AuthorDTO.from(author);

        return ResponseEntity.ok().body(authorDto);
    }

    @PostMapping
    public ResponseEntity<Integer> addOrUpdateAuthor(@Valid @RequestBody AuthorDTO authorDto){
        Author author=new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        return ResponseEntity.ok().body(authorServices.addOrUpdateAuthor(author));
    }

}
