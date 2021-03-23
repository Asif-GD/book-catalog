package com.catalog.service.bookcatalog.service;

import com.catalog.service.bookcatalog.datasource.entity.Author;
import com.catalog.service.bookcatalog.datasource.entity.Book;
import com.catalog.service.bookcatalog.datasource.repositories.AuthorRepository;
import com.catalog.service.bookcatalog.datasource.repositories.BookRepository;
import com.catalog.service.bookcatalog.model.request.CreateBookRequest;
import java.util.Objects;
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

    Book bookByIsbn = bookRepository.findByISBN(request.getIsbn());
    if (Objects.isNull(bookByIsbn)) {
      Book book = new Book();
      book.setTitle(request.getTitle());
      book.setIsbn(request.getIsbn());

      request.getAuthors().forEach(author -> {
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
}
