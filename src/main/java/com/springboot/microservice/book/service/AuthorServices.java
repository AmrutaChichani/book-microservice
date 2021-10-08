package com.springboot.microservice.book.service;

import com.springboot.microservice.book.dao.AuthorDAO;
import com.springboot.microservice.book.entity.Author;
import com.springboot.microservice.book.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthorServices {

    @Autowired

    private AuthorDAO authorsDao;

    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        authorsDao.findAll().forEach(authors::add);
        return authors;
    }

    public Author getAuthorById(Integer authorId) {
        Optional<Author> authorOptional =authorsDao.findById(authorId);
        if(!authorOptional.isPresent())
        {
            log.info("Invalid author Id :{}",authorId);
            throw new BadRequestException();
        }
        return authorOptional.get();
    }

    public Integer addOrUpdateAuthor(Author author)
    {
        authorsDao.save(author);
        return author.getId();
    }

    public List<Author> getAllAuthorsById(List<Integer> authorList) {
        List<Author> bookAuthorList=new ArrayList<>();
        authorsDao.findAllById(authorList).forEach(bookAuthorList::add);
        return bookAuthorList;
    }
}
