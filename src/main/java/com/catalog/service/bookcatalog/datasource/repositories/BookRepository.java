package com.catalog.service.bookcatalog.datasource.repositories;

import com.catalog.service.bookcatalog.datasource.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
