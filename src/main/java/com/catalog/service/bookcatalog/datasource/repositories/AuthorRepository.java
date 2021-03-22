package com.catalog.service.bookcatalog.datasource.repositories;

import com.catalog.service.bookcatalog.datasource.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
