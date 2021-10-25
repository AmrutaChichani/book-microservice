package com.springboot.microservice.book.dao;

import com.springboot.microservice.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface BookDAO extends JpaRepository<Book, Integer>,JpaSpecificationExecutor<Book> {
    public Page<Book> findAll(Specification specs, Pageable pageable);
}
