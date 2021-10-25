package com.springboot.microservice.book.controller;

import com.springboot.microservice.book.dto.BookDTO;
import com.springboot.microservice.book.dto.BookResponseDTO;
import com.springboot.microservice.book.entity.Book;
import com.springboot.microservice.book.filter.SearchCriteria;
import com.springboot.microservice.book.filter.SearchOperation;
import com.springboot.microservice.book.service.BookServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping
    public ResponseEntity<Page<Book>> getCatalog(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name="size",defaultValue = "3") Integer size,
            @RequestParam(required=false,name="sortBy", defaultValue = "id") String sortBy,
            @RequestParam(required = false,name = "FilterByCategory") String category,
            @RequestParam(required = false,name = "FilterByAuthor") Integer authorId,
            @RequestParam(required = false,name = "FilterByPublisher") Integer publisherId
            ) {

        List<SearchCriteria> criteriaList= new ArrayList<>();
        if(category!=null){
            SearchCriteria sc=new SearchCriteria();
            sc.setKey("category");
            sc.setValue(category);
            sc.setOperation(SearchOperation.MATCH);
            criteriaList.add(sc);
        }
        if(authorId!=null){
            SearchCriteria sc=new SearchCriteria();
            sc.setKey("author_id");
            sc.setValue(authorId.toString());
            sc.setOperation(SearchOperation.EQUAL);
            criteriaList.add(sc);
        }
        if(publisherId!=null){
            SearchCriteria sc=new SearchCriteria();
            sc.setKey("publisher");
            sc.setValue(publisherId.toString());
            sc.setOperation(SearchOperation.EQUAL);
            criteriaList.add(sc);
        }
        Page<Book> bookPages= bookServices.getCatalog(page, size, sortBy,criteriaList);
        return ResponseEntity.ok().body(bookPages);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable("bookId") Integer bookId) {
        return ResponseEntity.ok().body(bookServices.getBook(bookId));

    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Integer> deleteBook(@PathVariable("bookId") Integer bookId) {
        bookServices.deleteBook(bookId);
        return ResponseEntity.ok().body(bookId);
    }

    @PostMapping
    public ResponseEntity<Integer> addOrUpdateBook(@Valid @RequestBody BookDTO bookDto) {
        return ResponseEntity.ok().body(bookServices.addOrUpdateBook(bookDto));
    }

}
