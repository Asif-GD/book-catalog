package com.catalog.service.bookcatalog;

import com.catalog.service.bookcatalog.datasource.entity.Author;
import com.catalog.service.bookcatalog.datasource.entity.Book;
import com.catalog.service.bookcatalog.datasource.repositories.AuthorRepository;
import com.catalog.service.bookcatalog.datasource.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Author asif = new Author();
    asif.setFirstName("Asif");
    asif.setLastName("Iqbal");

    Book brain = new Book();
    brain.setTitle("The Brain");
    brain.setIsbn("13532");

    brain.getAuthors().add(asif);
    asif.getBooks().add(brain); //getBooks retrieves the set and not the values in the set.

    /* Equivalent to the above line

    Set<Book> bookSet = asif.getBooks();
    bookSet.add(brain);
    asif.setBooks(bookSet);
    */

    authorRepository.save(asif);
    bookRepository.save(brain);

    System.out.println("Bootstrap Success!");
    System.out.println("Number of books = " + bookRepository.count());
  }
}
