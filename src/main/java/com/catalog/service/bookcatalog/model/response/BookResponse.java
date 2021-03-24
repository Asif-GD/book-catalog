package com.catalog.service.bookcatalog.model.response;

import com.catalog.service.bookcatalog.model.BookInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {

  private BookInfo bookInfo;
}
