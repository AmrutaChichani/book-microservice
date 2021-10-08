package com.springboot.microservice.book.dto;

import com.springboot.microservice.book.entity.Publisher;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PublisherDTO {
    private Integer id;

    @NotBlank
    private String name;

    @Pattern(regexp = "^[0-9]{10}$",message = "only numeric value of 10 digits")
    private String contactNo;

    public static PublisherDTO from(Publisher publisher){
        PublisherDTO dto = new PublisherDTO();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        dto.setContactNo(publisher.getContactNo());
        return dto;
    }
}
