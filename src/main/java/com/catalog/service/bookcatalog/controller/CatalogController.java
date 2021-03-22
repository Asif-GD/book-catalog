package com.catalog.service.bookcatalog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatalogController {

  @GetMapping(value = "/hello")
  public String sayHello() {
    return "Say Hello";
  }
}
