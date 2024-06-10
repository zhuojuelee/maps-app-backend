package com.example.maps.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WeatherClientConfig {

  private final String BASE_URL = "https://api.open-meteo.com";

  public RestClient getWeatherClient() {
    return RestClient.builder().baseUrl(BASE_URL).build();
  }

  @Bean
  public WeatherClient weatherRestClient() {
    return new WeatherClient(getWeatherClient());
  }
}
