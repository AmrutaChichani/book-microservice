package com.springboot.microservice.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="author_id")
    private Integer id;

    @NotBlank
    @Column(name="author_name")
    private String name;

    @Email(message = "Enter valid email ID")
    @Column(name="email")
    private String email;

    @JsonIgnore
    @ManyToMany(mappedBy="authors", cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.PERSIST})
    private List<Book> books;

}
