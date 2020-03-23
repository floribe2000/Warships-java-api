package de.floribe2000.warships_java.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representation of all current API regions (RU, EU, NA, ASIA) containing the base URLs for API requests
 */
@AllArgsConstructor
public enum Region {

  RU("https://api.worldofwarships.ru/"),
  EU("https://api.worldofwarships.eu/"),
  NA("https://api.worldofwarships.com/"),
  ASIA("https://api.worldofwarships.asia/");

  @Getter
  private String baseURL;
}
