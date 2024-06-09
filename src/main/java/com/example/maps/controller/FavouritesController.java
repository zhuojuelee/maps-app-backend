package com.example.maps.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.maps.model.body.GetFavouritePlacesResponseBody;
import com.example.maps.model.pojo.FavouritePlace;
import com.example.maps.service.FavouritePlacesService;

@RestController
@RequestMapping("/favourites")
public class FavouritesController {

  private FavouritePlacesService favouritePlacesService;

  public FavouritesController(FavouritePlacesService favouritePlacesService) {
    this.favouritePlacesService = favouritePlacesService;
  }

  @GetMapping
  public ResponseEntity<GetFavouritePlacesResponseBody> getAllFavouritePlaces() {
    GetFavouritePlacesResponseBody body = GetFavouritePlacesResponseBody.builder()
        .favourites(favouritePlacesService.getAllFavouritePlaces())
        .build();

    return ResponseEntity.ok().body(body);
  }

  @PostMapping
  public ResponseEntity<FavouritePlace> addNewFavouritePlace(@RequestBody FavouritePlace place) {
    return ResponseEntity.ok().body(favouritePlacesService.saveFavouritePlace(place));
  }

  @DeleteMapping("/{placeId}")
  public ResponseEntity<String> deleteFavouritePlace(@PathVariable String placeId) {
    favouritePlacesService.deleteFavouritePlaceById(placeId);
    return ResponseEntity.ok().body("Successfully deleted place");
  }
}
