package com.example.maps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.maps")
@EnableJpaRepositories("com.example.maps.repository")
@EnableTransactionManagement
public class MapsApplication {

  public static void main(String[] args) {
    SpringApplication.run(MapsApplication.class, args);
  }

}
