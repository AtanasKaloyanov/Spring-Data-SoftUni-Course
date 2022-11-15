package com.example.springintro.service.impl;

import com.example.springintro.dto.BookInformation;
import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
        return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }

    // Task 1
    @Override
    public List<Book> findAllBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction)
                .orElseThrow(NoSuchElementException::new);
    }

    // Task 2
    @Override
    public List<Book> findAllByEditionTypeAndCopies(EditionType editionType, Integer copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies)
                .orElseThrow(NoSuchElementException::new);
    }

    // Task 3
    @Override
    public List<Book> findAllBooksWithPriceBetweenTwoIntegers(BigDecimal firstNumber, BigDecimal secondNumber) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(firstNumber, secondNumber)
                .orElseThrow(NoSuchElementException::new);
    }

    // Task 4
    @Override
    public List<Book> findAllBooksNotInYear(String givenYear) {
        return this.bookRepository.findAllByReleaseDateIsNotContaining(givenYear)
                .orElseThrow(NoSuchElementException::new);
    }

    // Task 5
    @Override
    public List<Book> findAllBooksBefore(LocalDate releasedYeard) {
        return this.bookRepository.findAllByReleaseDateBefore(releasedYeard);
    }

    // Task 7
    @Override
    public List<Book> findAllBooksContainingGivenString(String regex) {
        return this.bookRepository.findAllByTitleContaining(regex)
                .orElseThrow(NoSuchElementException::new);
    }

    // Task 8
    @Override
    public List<Book> findAllBooksAuthorsLastNameStartingWith(String regex) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(regex)
                .orElseThrow(NoSuchElementException::new);
    }

    // Task 9
    @Override
    public int booksWithTitleLongerThanSymbols(int givenNumber) {
        return this.bookRepository.countBooksWithTitleWithMoreLettersThan(givenNumber)
                .orElseThrow(NoSuchElementException::new);
    }

    // Task 11
    @Override
    public BookInformation booksInformationByGivenTitle(String title) {
        return this.bookRepository.booksInformationByGivenTitle(title)
                .orElseThrow(NoSuchElementException::new);
    }


}



