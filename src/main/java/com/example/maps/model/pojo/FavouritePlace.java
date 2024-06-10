package com.example.maps.model.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "favourites")
public class FavouritePlace {

  @Id
  private String placeId;
  private String name;
  private Float lat;
  private Float lng;

  public FavouritePlace() {
  }
}
