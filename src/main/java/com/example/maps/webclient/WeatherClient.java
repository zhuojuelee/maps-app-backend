package com.example.maps.webclient;

import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeatherClient {

  private final RestClient restClient;

  public WeatherClient(RestClient restClient) {
    this.restClient = restClient;
  }

  public Float getWeatherByLatLong(Float lat, Float lng) {
    try {
      String uriStr = String.format("/v1/forecast?latitude=%f&longitude=%f",
          Float.valueOf(lat), Float.valueOf(lng));
      log.info("[WeatherClient] Getting weather info for lat:{} and long: {}", lat, lng);
      String response = this.restClient.get()
          .uri(uriStr + "&current=temperature_2m")
          .retrieve()
          .body(String.class);

      ObjectMapper mapper = new ObjectMapper();
      JsonNode weatherObject = mapper.readTree(response);
      String temp = weatherObject.path("current")
          .path("temperature_2m")
          .asText();

      return Float.parseFloat(temp);
    } catch (JsonProcessingException error) {
      log.info("[WeatherClient] Unable to deserialize response: {}", error.getMessage());
      return null;
    }
  }
}
