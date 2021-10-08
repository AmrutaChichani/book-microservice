package com.springboot.microservice.book.controller;

import com.springboot.microservice.book.dto.BookDTO;
import com.springboot.microservice.book.dto.BookResponseDTO;
import com.springboot.microservice.book.service.BookServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getCatalog() {

        return ResponseEntity.ok().body(bookServices.getCatalog());
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
