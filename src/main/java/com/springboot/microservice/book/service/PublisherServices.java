package com.springboot.microservice.book.service;

import com.springboot.microservice.book.dao.PublisherDAO;
import com.springboot.microservice.book.dto.PublisherDTO;
import com.springboot.microservice.book.entity.Publisher;
import com.springboot.microservice.book.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PublisherServices {
    @Autowired
    private PublisherDAO publishersDao;

    public List<PublisherDTO> getPublishers()
    {
        List<PublisherDTO> publishers=new ArrayList<>();
        publishersDao.findAll().forEach(publisher->publishers.add(PublisherDTO.from(publisher)));
        return publishers;

    }

    public Publisher getPublisherById(Integer publisherId)
    {
        Optional<Publisher> publisherOptional=publishersDao.findById(publisherId);
        if(!publisherOptional.isPresent()){
            log.info("Invalid publisher id:{}",publisherId);
            throw new BadRequestException();
        }
        return publisherOptional.get();

    }

    public Integer addOrUpdatePublisher(Publisher publisher)
    {
        publishersDao.save(publisher);
        return publisher.getId();
    }
}
