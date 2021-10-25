package com.springboot.microservice.book.filter;

import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private String value;
    private SearchOperation operation;
}
