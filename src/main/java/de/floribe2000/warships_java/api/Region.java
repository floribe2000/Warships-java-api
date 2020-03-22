package de.floribe2000.warships_java.api;

public enum Region {

  RU("https://api.worldofwarships.ru"),
  EU("https://api.worldofwarships.eu"),
  NA("https://api.worldofwarships.com"),
  ASIA("https://api.worldofwarships.asia");

  private final String baseURL;

  Region(String baseURL) {
    this.baseURL = baseURL;
  }

  public String getBaseURL() {
    return baseURL;
  }
}
