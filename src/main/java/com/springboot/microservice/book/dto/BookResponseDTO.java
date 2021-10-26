package com.springboot.microservice.book.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.microservice.book.entity.Book;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BookResponseDTO {


    private Integer id;
    private String title;
    private String category;
    private Double price;

    @JsonFormat(timezone="IST",pattern = "yyyy-MM-dd")
    private Date publishDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @JsonFormat(timezone = "IST",pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date lastModifiedDate;

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
        dto.setCreateDate(book.getCreateDate());
        dto.setLastModifiedDate(book.getLastModifiedDate());

        List<AuthorDTO> authorsDtoList=new ArrayList<>();
        book.getAuthors().forEach(author->authorsDtoList.add(AuthorDTO.from(author)));
        dto.setAuthors(authorsDtoList);

        dto.setPublisher(PublisherDTO.from(book.getPublisher()));

        return dto;
    }

}
