package com.catalog.service.bookcatalog.service;

import com.catalog.service.bookcatalog.datasource.entity.Author;
import com.catalog.service.bookcatalog.datasource.entity.Book;
import com.catalog.service.bookcatalog.datasource.repositories.AuthorRepository;
import com.catalog.service.bookcatalog.datasource.repositories.BookRepository;
import com.catalog.service.bookcatalog.model.BookInfo;
import com.catalog.service.bookcatalog.model.request.BookAuthor;
import com.catalog.service.bookcatalog.model.request.CreateBookRequest;
import com.catalog.service.bookcatalog.model.response.BookResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  /*Constructor based dependency injection
   * RECOMMENDED*/
  public CatalogService(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public Long saveBooks(CreateBookRequest request) {

    final BookInfo bookInfo = request.getBookInfo();

    Book bookByIsbn = bookRepository.findByISBN(bookInfo.getIsbn());
    if (Objects.isNull(bookByIsbn)) {
      Book book = new Book();
      book.setTitle(bookInfo.getTitle());
      book.setIsbn(bookInfo.getIsbn());

      bookInfo.getAuthors().forEach(author -> {
        Author authorByName = authorRepository
            .findByName(author.getFirstName(), author.getLastName());
        if (Objects.isNull(authorByName)) {
          Author newAuthor = new Author();
          newAuthor.setFirstName(author.getFirstName());
          newAuthor.setLastName(author.getLastName());
          newAuthor.getBooks().add(book);
          authorRepository.save(newAuthor);

          book.getAuthors().add(newAuthor);
        }
      });
      return bookRepository.save(book).getId();
    }
    return null;
  }

  public BookResponse displayBookDetails(Long id) {

    Optional<Book> requestedBook = bookRepository.findById(id);

    if (requestedBook.isPresent()) {
      final Book book = requestedBook.get();

      BookAuthor bookAuthor = new BookAuthor();
      List<BookAuthor> bookAuthorsList = new ArrayList<>();

      book.getAuthors().forEach(author -> {
        bookAuthor.setFirstName(author.getFirstName());
        bookAuthor.setLastName(author.getLastName());
        bookAuthorsList.add(bookAuthor);
      });

      BookInfo bookInfo = BookInfo.builder()
          .title(book.getTitle())
          .isbn(book.getIsbn())
          .authors(bookAuthorsList)
          .build();

      return BookResponse.builder()
          .bookInfo(bookInfo)
          .build();
    }
    throw new NoSuchElementException("Book with id: " + id + " not found");
  }
}
