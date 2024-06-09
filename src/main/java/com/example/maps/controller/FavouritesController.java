package com.example.maps.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavouritesController {

  @GetMapping("/favourites")
  public String index() {
    return "Hello";
  }
}
