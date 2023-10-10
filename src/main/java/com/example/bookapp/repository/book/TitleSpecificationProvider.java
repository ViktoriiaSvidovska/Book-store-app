package com.example.bookapp.repository.book;

import com.example.bookapp.model.Book;
import com.example.bookapp.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FIELD_NAME = "title";
    private static final String FILTER_KEY = "titlesLike";

    @Override
    public String getKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(FIELD_NAME), "%" + params[0] + "%");
    }
}
