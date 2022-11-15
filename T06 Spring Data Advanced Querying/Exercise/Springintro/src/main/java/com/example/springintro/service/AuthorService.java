package com.example.springintro.service;

import com.example.springintro.dto.AuthorCopies;
import com.example.springintro.model.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    // Task 6
    List<Author> findAllByFirstNameStartsWith(String regex);

    // Task 10
    List<AuthorCopies> authorsAndTotalBookCopies();
}
