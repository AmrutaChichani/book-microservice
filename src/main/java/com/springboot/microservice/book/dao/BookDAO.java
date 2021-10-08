package com.springboot.microservice.book.dao;

import com.springboot.microservice.book.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDAO extends CrudRepository<Book, Integer> {
}
