package com.springboot.microservice.book.dao;

import com.springboot.microservice.book.entity.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherDAO extends CrudRepository<Publisher, Integer> {
}
