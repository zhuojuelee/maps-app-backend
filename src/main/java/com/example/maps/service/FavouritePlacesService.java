package com.example.maps.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.maps.model.pojo.FavouritePlace;
import com.example.maps.repository.FavouritePlacesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FavouritePlacesService {

  private FavouritePlacesRepository favouritePlacesRepository;

  public FavouritePlacesService(FavouritePlacesRepository favouritePlacesRepository) {
    this.favouritePlacesRepository = favouritePlacesRepository;
  }

  @Transactional
  public List<FavouritePlace> getAllFavouritePlaces() {
    log.info("Fetching all favourite places");
    return favouritePlacesRepository.findAll();
  }

  @Transactional
  public FavouritePlace saveFavouritePlace(FavouritePlace place) {
    log.info("Saved {} in favourites", place.getName());
    favouritePlacesRepository.save(place);
    return place;
  }

  @Transactional
  public void deleteFavouritePlaceById(String id) {
    favouritePlacesRepository.deleteById(id);
  }
}
