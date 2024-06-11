package com.example.maps.controller;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/favourites")
public class FavouritesController {

  private FavouritePlacesService favouritePlacesService;

  public FavouritesController(FavouritePlacesService favouritePlacesService) {
    this.favouritePlacesService = favouritePlacesService;
  }

  @GetMapping
  public ResponseEntity<GetFavouritePlacesResponseBody> getAllFavouritePlaces(
      @PathVariable("page") Optional<Integer> page,
      @PathVariable("size") Optional<Integer> size) {

    log.info("[Controller] Received a get request with page {} and size {}", page, size);

    GetFavouritePlacesResponseBody body;
    if (!page.isPresent() || !size.isPresent()) {
      body = GetFavouritePlacesResponseBody.builder()
          .favourites(favouritePlacesService.getAllFavouritePlaces(0, 0))
          .build();
    } else {
      body = GetFavouritePlacesResponseBody.builder()
          .favourites(favouritePlacesService.getAllFavouritePlaces(page.get(), size.get()))
          .build();
    }

    return ResponseEntity.ok().body(body);
  }

  @GetMapping(value = "/{placeId}/weather")
  public ResponseEntity<Float> getPlaceWeather(@PathVariable String placeId) {
    log.info("[Controller] Getting temperature for {}", placeId);
    return ResponseEntity.ok().body(favouritePlacesService.getPlaceWeather(placeId));
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FavouritePlace> addNewFavouritePlace(@RequestBody FavouritePlace place) {
    log.info("[Controller] Received a request to save {}", place.toString());
    return ResponseEntity.ok().body(favouritePlacesService.saveFavouritePlace(place));
  }

  @DeleteMapping("/{placeId}")
  public ResponseEntity<String> deleteFavouritePlace(@PathVariable String placeId) {
    log.info("[Controller] Received a request to delete {}", placeId);
    favouritePlacesService.deleteFavouritePlaceById(placeId);
    return ResponseEntity.ok().body("Successfully deleted place");
  }
}
