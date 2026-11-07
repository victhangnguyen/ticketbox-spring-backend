// Path: src/main/java/com/ticketbox/springcoredemo/service/ConsoleLoggingService.java
package com.ticketbox.springcoredemo.service;

import org.springframework.stereotype.Component;

@Component
public class ConsoleLoggingService implements LoggingService {

  @Override
  public void log(String message) {
    System.out.println("[LOG] " + message);
  }
}