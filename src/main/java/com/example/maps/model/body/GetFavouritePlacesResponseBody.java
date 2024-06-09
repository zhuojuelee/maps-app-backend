package com.example.maps.model.body;

import java.util.List;

import com.example.maps.model.pojo.FavouritePlace;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetFavouritePlacesResponseBody {
  @JsonProperty
  private List<FavouritePlace> favourites;
}
