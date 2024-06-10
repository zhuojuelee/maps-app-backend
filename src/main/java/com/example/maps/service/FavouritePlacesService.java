package com.example.maps.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.maps.model.pojo.FavouritePlace;
import com.example.maps.repository.FavouritePlacesRepository;
import com.example.maps.webclient.WeatherClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FavouritePlacesService {

  private FavouritePlacesRepository favouritePlacesRepository;
  private WeatherClient weatherClient;

  public FavouritePlacesService(
      FavouritePlacesRepository favouritePlacesRepository,
      WeatherClient weatherClient) {
    this.favouritePlacesRepository = favouritePlacesRepository;
    this.weatherClient = weatherClient;
  }

  @Transactional
  public List<FavouritePlace> getAllFavouritePlaces(int page, int size) {
    log.info("Fetching all favourite places");

    if (page != 0 && size != 0) {
      Pageable pageable = PageRequest.of(page, size);
      Page<FavouritePlace> pageResults = favouritePlacesRepository.findAll(pageable);

      return pageResults.getContent();
    }

    return favouritePlacesRepository.findAll();
  }

  public float getPlaceWeather(String placeId) {
    FavouritePlace place = favouritePlacesRepository.getReferenceById(placeId);
    Float temperature = weatherClient.getWeatherByLatLong(place.getLat(), place.getLng());

    if (temperature == null) {
      return (float) 99999.00;
    }

    return temperature;
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
