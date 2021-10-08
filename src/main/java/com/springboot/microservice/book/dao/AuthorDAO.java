package com.springboot.microservice.book.dao;

import com.springboot.microservice.book.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorDAO extends CrudRepository<Author, Integer> {
}
