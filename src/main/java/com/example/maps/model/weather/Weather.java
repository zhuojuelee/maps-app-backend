package com.example.maps.model.weather;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Weather {

  private String lattitude;
  private String longitude;
  private Float temperature;

  @JsonProperty("current")
  private void unpackCurrent(Map<String, Float> current) {
    temperature = current.get("temperature_2m");
  }
}
