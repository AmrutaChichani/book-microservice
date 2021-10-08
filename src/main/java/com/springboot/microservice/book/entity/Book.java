package com.springboot.microservice.book.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name= "book")
@SQLDelete(sql="UPDATE book SET active_flag=0 where book_id=?")
@Where(clause="active_flag=1")
public class Book {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="book_id")
    private Integer id;

    @NotBlank
    @Column(name="book_name")
    private String name;

    @Column(name="category")
    private String category;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="publish_date")
    private Date publishDate;

    @ManyToOne
    @JoinColumn(name="publisher_id")
    private Publisher publisher;

    @Column(name="price")
    private double price;


    @Column(name="active_flag",columnDefinition = "TINYINT default 1", length = 1)
    private Integer activeFlag;

    @ManyToMany(cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    @JoinTable(name="book_author" , joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<Author> authors;


}