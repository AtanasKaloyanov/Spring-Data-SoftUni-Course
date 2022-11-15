package com.example.springintro.repository;

import com.example.springintro.dto.AuthorCopies;
import com.example.springintro.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    // Task 6
    Optional<List<Author>> findAllByFirstNameEndingWith(String regex);

    // Task 10
    @Query("SELECT a.firstName AS firstName, a.lastName AS lastName, SUM(b.copies) AS bookCopies" +
            " FROM Author a" +
            " JOIN a.books b" +
            " GROUP BY a" +
            " ORDER BY bookCopies DESC")

    Optional<List<AuthorCopies>> findAllAuthorsWithCopiesCountOrderByCopiesDesc();
}
