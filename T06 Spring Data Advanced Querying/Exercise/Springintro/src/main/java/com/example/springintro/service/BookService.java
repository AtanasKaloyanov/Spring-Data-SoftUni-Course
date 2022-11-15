package com.example.springintro.service;

import com.example.springintro.dto.BookInformation;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    // Task 1
    List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    // Task 2
    List<Book> findAllByEditionTypeAndCopies(EditionType editionType, Integer copies);

    // Task 3
    List<Book> findAllBooksWithPriceBetweenTwoIntegers(BigDecimal firstNumber, BigDecimal secondNumber);

    // Task 4
    List<Book> findAllBooksNotInYear(String givenYear);

    // Task 5
    List<Book> findAllBooksBefore(LocalDate releasedYeard);

    // Task 7
    List<Book> findAllBooksContainingGivenString(String regex);

    // Task 8
    List<Book> findAllBooksAuthorsLastNameStartingWith(String regex);

    // Task 9
    int booksWithTitleLongerThanSymbols(int givenNumber);

    // Task 11
    BookInformation booksInformationByGivenTitle(String title);
}
