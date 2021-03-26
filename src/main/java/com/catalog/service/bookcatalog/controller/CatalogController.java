package com.catalog.service.bookcatalog.controller;

import com.catalog.service.bookcatalog.model.request.CreateBookRequest;
import com.catalog.service.bookcatalog.model.response.BookResponse;
import com.catalog.service.bookcatalog.service.CatalogService;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {

  private final CatalogService catalogService;

  public CatalogController(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  @PostMapping(value = "/books")
  public ResponseEntity<URL> saveBooks(@RequestBody CreateBookRequest request)
      throws MalformedURLException {
    Long bookId = catalogService.saveBooks(request);
    URL bookUrl = new URL("http://localhost:8080/book/" + bookId);
    return ResponseEntity.ok(bookUrl);
  }

  @GetMapping("/book/{id}")
  public ResponseEntity<BookResponse> displayBookDetails(@PathVariable("id") Long id) {
    final BookResponse bookResponse = catalogService.displayBookDetails(id);
    return ResponseEntity.ok(bookResponse);
  }

  @DeleteMapping("/book/{isbn}")
  public ResponseEntity<String> removeBook(@PathVariable("isbn") String isbn) {
    String bookStatus = catalogService.removeBook(isbn);
    return ResponseEntity.ok(bookStatus);
  }
}
