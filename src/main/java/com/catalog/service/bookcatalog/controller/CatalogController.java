package com.catalog.service.bookcatalog.controller;

import com.catalog.service.bookcatalog.model.request.CreateBookRequest;
import com.catalog.service.bookcatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {

  @Autowired //Spring annotation to instruct spring to handle this component - Not Recommended
  private CatalogService catalogService;

  @PostMapping(value = "/books")
  public ResponseEntity<String> saveBooks(@RequestBody CreateBookRequest request) {
    Long bookId = catalogService.saveBooks(request);
    String url = "http://localhost:8080/book/" + Long.toString(bookId);
    return ResponseEntity.ok(url);
  }
}
