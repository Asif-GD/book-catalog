package com.catalog.service.bookcatalog.datasource.repositories;

import com.catalog.service.bookcatalog.datasource.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

  @Query(value = "select * from book where isbn = :isbn", nativeQuery = true)
  Book findByISBN(String isbn);
}
