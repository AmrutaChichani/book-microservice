package com.springboot.microservice.book.service;

import com.springboot.microservice.book.dao.BookDAO;
import com.springboot.microservice.book.dto.BookDTO;
import com.springboot.microservice.book.dto.BookResponseDTO;
import com.springboot.microservice.book.entity.Book;
import com.springboot.microservice.book.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServices {

    @Autowired
    private BookDAO booksDao;

    @Autowired
    private AuthorServices authorServices;

    @Autowired
    private PublisherServices publisherServices;

    public List<BookResponseDTO> getCatalog() {
        List<BookResponseDTO> result=new ArrayList<>();
        booksDao.findAll().forEach(book ->
                result.add(BookResponseDTO.from(book)));
        return result;
    }

    public BookResponseDTO getBook(int bookId) {

        Optional<Book> bookOptional=booksDao.findById(bookId);
        if(!bookOptional.isPresent())
        {
            log.info("Invalid book id:{}",bookId);
            throw new BadRequestException();
        }
        return BookResponseDTO.from(bookOptional.get());
    }

    public Integer addOrUpdateBook(BookDTO bookDto) {
        Book book=new Book();
        book.setId(bookDto.getId());
        book.setActiveFlag(1);
        book.setCategory(bookDto.getCategory());
        book.setPublishDate(bookDto.getPublishDate());
        book.setPrice(bookDto.getPrice());
        book.setName(bookDto.getTitle());
        book.setAuthors(authorServices.getAllAuthorsById(bookDto.getAuthorId()));
        book.setPublisher(publisherServices.getPublisherById(bookDto.getPublisherId()));

        booksDao.save(book);
        return book.getId();
    }

    public void deleteBook(int bookId) {
        if(booksDao.existsById(bookId)) {
            booksDao.deleteById(bookId);
        }else{
            log.info("Invalid book id:{}",bookId);
            throw new BadRequestException();
        }
    }

}
