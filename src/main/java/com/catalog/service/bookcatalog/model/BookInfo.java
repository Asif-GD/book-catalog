package com.catalog.service.bookcatalog.model;

import com.catalog.service.bookcatalog.model.BookInfo.BookInfoBuilder;
import com.catalog.service.bookcatalog.model.request.BookAuthor;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = BookInfoBuilder.class)
public class BookInfo {

  @JsonPOJOBuilder(withPrefix = "")
  public static final class BookInfoBuilder {

  }

  private String title;
  private String isbn;
  private List<BookAuthor> authors;
}
