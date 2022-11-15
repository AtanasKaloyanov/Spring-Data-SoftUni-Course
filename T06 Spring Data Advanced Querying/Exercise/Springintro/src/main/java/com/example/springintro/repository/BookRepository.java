package com.example.springintro.repository;

import com.example.springintro.dto.BookInformation;
import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    // Task 1
    Optional<List<Book>> findAllByAgeRestriction(AgeRestriction ageRestriction);

    // Task 2
    Optional<List<Book>> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    // Task 3
    Optional<List<Book>> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal priceGreaterThan, BigDecimal priceLessThan);

    // Task 4
    @Query("SELECT b FROM Book AS b " +
            "WHERE substring( b.releaseDate, 1, 4) NOT IN :releasedYear")
    Optional<List<Book>> findAllByReleaseDateIsNotContaining(String releasedYear);

    // Task 5
    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    // Task 7
    Optional<List<Book>> findAllByTitleContaining(String regex);

    // Task 8
    Optional<List<Book>> findAllByAuthorLastNameStartingWith(String regex);

    // Task 9
    @Query("SELECT COUNT(b.id) FROM Book AS b " +
            "WHERE LENGTH(b.title) > :givenNumber")
    Optional<Integer> countBooksWithTitleWithMoreLettersThan(int givenNumber);

    // Task 11
    //title, edition type, age restriction and price
    @Query("SELECT new com.example.springintro.dto.BookInformation(b.title, b.editionType, b.ageRestriction, b.price)" +
            " FROM Book b" +
            " WHERE b.title = :givenTitle")

    Optional<BookInformation> booksInformationByGivenTitle(String givenTitle);
}
