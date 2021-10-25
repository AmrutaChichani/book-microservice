package com.springboot.microservice.book.filter;

import com.springboot.microservice.book.entity.Author;
import com.springboot.microservice.book.entity.Book;
import com.springboot.microservice.book.entity.Publisher;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification implements Specification<Book> {

    private final List<SearchCriteria> criteriaList;

    public BookSpecification(List<SearchCriteria> criteria){
        this.criteriaList=criteria;
    }


    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates=new ArrayList<>();
        for(SearchCriteria criteria:criteriaList){
            if(criteria.getOperation().equals(SearchOperation.EQUAL)) {
                if(criteria.getKey().equals("publisher")){
                    Join<Publisher,Book> bookPublisherJoin=root.join("publisher");
                    Path<Publisher> expression= bookPublisherJoin.get("id");
                    predicates.add(criteriaBuilder.equal(expression, criteria.getValue()));
                }else{
                    Join<Author,Book> authorJoin= root.join("authors");
                    Path<Author> expression=authorJoin.get("id");
                    predicates.add(criteriaBuilder.equal(expression, criteria.getValue()));
                }
            }else
                predicates.add(criteriaBuilder.like(root.get( criteria.getKey()),"%"+criteria.getValue()+"%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
