package com.springboot.microservice.book.controller;

import com.springboot.microservice.book.dto.BookDTO;
import com.springboot.microservice.book.dto.BookResponseDTO;
import com.springboot.microservice.book.entity.Book;
import com.springboot.microservice.book.service.BookServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<List<BookResponseDTO>> getCatalog(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(required=false,name="sortBy") String sortBy) {
        Page<Book> bookPages;
        if(sortBy!=null) {

            bookPages = bookServices.getCatalog(page, size, sortBy);
        }else{
            bookPages =bookServices.getCatalog(page,size);
        }

        return buildResponse(bookPages);
    }

    private ResponseEntity<List<BookResponseDTO>> buildResponse(Page<Book> bookPages) {
        List<BookResponseDTO> result=new ArrayList<>();
        bookPages.getContent().forEach(book -> {
            result.add(BookResponseDTO.from(book));
        });
        HttpHeaders responseHeader=new HttpHeaders();
        responseHeader.set("total-elements", String.valueOf(bookPages.getTotalElements()));
        responseHeader.set("total-no-of-Pages", String.valueOf(bookPages.getTotalPages()));
        responseHeader.set("current-page",String.valueOf(bookPages.getNumber()));
        return ResponseEntity.ok().headers(responseHeader).body(result);
    }

    @GetMapping("/filter/publisher")
    public ResponseEntity<List<BookResponseDTO>> getCatalogByPublisherFilter( @RequestParam("page") Integer page,
                                                   @RequestParam("size") Integer size,
                                                   @RequestParam(name="publisherId") Integer publisher){
        Page<Book> catalogPages=bookServices.getCatalogByPublisherFilter(publisher,page,size);
        return buildResponse(catalogPages);
    }
    @GetMapping("/filter/author")
    public ResponseEntity<List<BookResponseDTO>> getCatalogByAuthorFilter( @RequestParam("page") Integer page,
                                                                   @RequestParam("size") Integer size,
                                                                   @RequestParam(name="authorId") Integer author){
        Page<Book> catalogPages=bookServices.getCatalogByAuthorFilter(author,page,size);
        return buildResponse(catalogPages);
    }
    @GetMapping("/filter/category")
    public ResponseEntity<List<BookResponseDTO>> getCatalogByCategoryFilter( @RequestParam("page") Integer page,
                                                                   @RequestParam("size") Integer size,
                                                                   @RequestParam(name="category") String category){
        Page<Book> catalogPages=bookServices.getCatalogByCategoryFilter(category,page,size);
        return buildResponse(catalogPages);
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
