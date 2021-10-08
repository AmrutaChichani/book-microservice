package com.springboot.microservice.book.controller;

import com.springboot.microservice.book.dto.PublisherDTO;
import com.springboot.microservice.book.entity.Publisher;
import com.springboot.microservice.book.service.PublisherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/publisher")
public class PublisherController {

    @Autowired
    private PublisherServices publisherServices;

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getPublishers() {
        List<PublisherDTO> result=publisherServices.getPublishers();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable("publisherId") Integer publisherId) {
        PublisherDTO result=PublisherDTO.from(publisherServices.getPublisherById(publisherId));
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<Integer> addOrUpdatePublisher(@Valid @RequestBody PublisherDTO publisher) {
        Publisher thePublisher=new Publisher();
        if(publisher.getId()!=null)
            thePublisher.setId(publisher.getId());
        thePublisher.setName(publisher.getName());
        thePublisher.setContactNo(publisher.getContactNo());
        Integer result= publisherServices.addOrUpdatePublisher(thePublisher);
        return ResponseEntity.ok().body(result);
    }
}
