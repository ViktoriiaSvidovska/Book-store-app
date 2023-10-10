package com.example.bookapp.repository.book;

import com.example.bookapp.model.Book;
import com.example.bookapp.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FIELD_NAME = "author";
    private static final String FILTER_KEY = "authorsLike";

    @Override
    public String getKey() {
        return FILTER_KEY;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(FIELD_NAME), "%" + params[0] + "%");
    }
}
