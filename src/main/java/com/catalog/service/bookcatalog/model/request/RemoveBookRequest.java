package com.catalog.service.bookcatalog.model.request;

import com.catalog.service.bookcatalog.model.BookInfo;
import lombok.Data;

@Data
public class RemoveBookRequest {

  private BookInfo bookInfo;

}
