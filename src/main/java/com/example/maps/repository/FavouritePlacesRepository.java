package com.example.maps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maps.model.pojo.FavouritePlace;

public interface FavouritePlacesRepository extends JpaRepository<FavouritePlace, String> {

}
