package com.catalog.service.bookcatalog.datasource.repositories;

import com.catalog.service.bookcatalog.datasource.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

  @Query(value = "select * from author where first_name = :firstName and last_name = :lastName", nativeQuery = true)
  Author findByName(String firstName, String lastName);
}
