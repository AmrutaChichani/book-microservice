package com.springboot.microservice.book.dao;

import com.springboot.microservice.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {
    public Page<Book> findByCategoryContaining(String category, Pageable pageable);

    @Query(value="SELECT * FROM book b, book_author ba WHERE b.book_id=ba.book_id and ba.author_id=?",nativeQuery = true)
    public Page<Book> findAllByAuthors(Integer author, Pageable pageable);

    @Query(value="SELECT * FROM book WHERE publisher_id=?",nativeQuery = true)
    public Page<Book> findAllByPublisher(Integer publisher,Pageable pageable);
}
