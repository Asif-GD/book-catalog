package com.catalog.service.bookcatalog.model.request;

import java.util.List;
import lombok.Data;

@Data
public class CreateBookRequest {

  private String title;
  private String isbn;
  private List<BookAuthor> authors;
}
