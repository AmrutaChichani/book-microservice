package com.springboot.microservice.book.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name="publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="publisher_id")
    private Integer id;

    @NotBlank
    @Column(name="name")
    private String name;

    @Pattern(regexp="^[0-9]{10}$",message = "Enter valid contact no.")
    @Column(name="contact_no")
    private String contactNo;

}

