package com.springboot.microservice.book.dto;

import com.springboot.microservice.book.entity.Book;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BookResponseDTO {


    private Integer id;
    private String title;
    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
    private Double price;
    private List<AuthorDTO> authors;
    private PublisherDTO publisher;

    public static BookResponseDTO from(Book book){
        BookResponseDTO dto = new BookResponseDTO();
        if(book.getId()!=null)
            dto.setId(book.getId());
        dto.setTitle(book.getName());
        dto.setPublishDate(book.getPublishDate());
        dto.setPrice(book.getPrice());
        dto.setCategory(book.getCategory());

        List<AuthorDTO> authorsDtoList=new ArrayList<>();
        book.getAuthors().forEach(author->authorsDtoList.add(AuthorDTO.from(author)));
        dto.setAuthors(authorsDtoList);

        dto.setPublisher(PublisherDTO.from(book.getPublisher()));

        return dto;
    }

}
