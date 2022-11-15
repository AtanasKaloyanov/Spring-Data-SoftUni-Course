package com.example.springintro;

import com.example.springintro.dto.BookInformation;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private BookService bookService;

    private Scanner scanner;

    private final int TASK_2_NUMBER_5000 = 5000;

    private final int TASK_3_NUMBER_5 = 5;
    private final int TASK_3_NUMBER_40 = 40;
    private final String TASK_3_PRINT_PATTERN = "%s - $%s%n";

    private final String TASK_5_PRINT_PATTERN = "%s %s %.2f%n";

    private final String TASK_6_PRINT_PATTERN = "%s %s%n";

    private final String TASK_8_PRINT_PATTERN = "%s %s %s%n";

    private final String TASK_10_PRINT_PATTERN = "%s %s - %s%n";

    private final String TASK_11_PRINT_PATTERN = "%s %s %s %.2f%n";


    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //   printAllAuthorsAndNumberOfTheirBooks();
        //  pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        //Task 1
//        final String input = scanner.nextLine().toUpperCase();
//        AgeRestriction ageRestriction = AgeRestriction.valueOf(input);
//
//        List<Book> searchedBooks = this.bookService.findAllBooksByAgeRestriction(ageRestriction);
//
//        for (Book currentBook : searchedBooks) {
//            System.out.println(currentBook.getTitle());
//        }

//        // Task 2
//        EditionType editionType = EditionType.GOLD;
//
//        List<Book> searchedBooks = this.bookService.findAllByEditionTypeAndCopies(editionType, TASK_2_NUMBER_5000);
//
//        for (Book currentBook : searchedBooks) {
//            System.out.println(currentBook.getTitle());
//        }

        // Task 3
//       BigDecimal firstNumber = BigDecimal.valueOf(TASK_3_NUMBER_5);
//       BigDecimal secondNumber = BigDecimal.valueOf(TASK_3_NUMBER_40);
//
//       List<Book> searchedBooks = this.bookService.findAllBooksWithPriceBetweenTwoIntegers(firstNumber, secondNumber);
//
//       for (Book currentBook : searchedBooks) {
//           System.out.printf(TASK_3_PRINT_PATTERN, currentBook.getTitle(), currentBook.getPrice());
//       }

        // Task 4
//        String givenYear = scanner.nextLine();
//
//        List<Book> searchedBooks = this.bookService.findAllBooksNotInYear(givenYear);
//
//        for (Book currentBook : searchedBooks) {
//            System.out.println(currentBook.getTitle());
//        }

        // Task 5
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
//        LocalDate givenDate = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);
//
//        List<Book> searchedBooks = this.bookService.findAllBooksBefore(givenDate);
//
//        for (Book currentBook : searchedBooks) {
//            System.out.printf(TASK_5_PRINT_PATTERN, currentBook.getTitle(), currentBook.getEditionType(), currentBook.getPrice());
//        }

        // Task 6
//        String regex = scanner.nextLine();
//
//        List<Author> searchedAuthors = this.authorService.findAllByFirstNameStartsWith(regex);
//
//        for (Author currentAuthor : searchedAuthors) {
//            System.out.printf(TASK_6_PRINT_PATTERN, currentAuthor.getFirstName(), currentAuthor.getLastName());
//        }

        // Task 7
//        String regex = scanner.nextLine();
//
//        List<Book> searchedBooks = this.bookService.findAllBooksContainingGivenString(regex);
//
//        for (Book currentBook : searchedBooks) {
//            System.out.println(currentBook.getTitle());
//        }

        // Task 8
//        String regex = scanner.nextLine();
//
//        List<Book> searchedBooks = this.bookService.findAllBooksAuthorsLastNameStartingWith(regex);
//
//        for (Book currentBook : searchedBooks) {
//            System.out.printf(TASK_8_PRINT_PATTERN, currentBook.getTitle(),
//                    currentBook.getAuthor().getFirstName(),
//                    currentBook.getAuthor().getLastName());
//        }

        // Task 9
//        int givenNumber = Integer.parseInt(scanner.nextLine());
//
//        int countSearchedBooks = this.bookService.booksWithTitleLongerThanSymbols(givenNumber);
//
//        System.out.println(countSearchedBooks);

        // Task 10
//        List<AuthorCopies> authorCopiesList = this.authorService.authorsAndTotalBookCopies();
//
//        for (AuthorCopies current : authorCopiesList) {
//            System.out.printf(TASK_10_PRINT_PATTERN, current.getFirstName(), current.getLastName(), current.getBookCopies());
//        }

        // Task 11
//        String givenTitle = scanner.nextLine();
//
//        BookInformation booksInformationByGivenTitle = this.bookService.booksInformationByGivenTitle(givenTitle);
//
//            System.out.printf(TASK_11_PRINT_PATTERN,
//                    booksInformationByGivenTitle.getTitle(), booksInformationByGivenTitle.getEditionType(),
//                    booksInformationByGivenTitle.getAgeRestriction(), booksInformationByGivenTitle.getPrice());
        }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }


}
