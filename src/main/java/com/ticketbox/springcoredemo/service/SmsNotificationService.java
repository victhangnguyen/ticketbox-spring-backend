// Path: src/main/java/com/ticketbox/springcoredemo/service/SmsNotificationService.java
package com.ticketbox.springcoredemo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationService implements NotificationService {

  public SmsNotificationService() {
    System.out.println("In constructor: SmsNotificationService");
  }

  @Override
  public void send(String message) {
    System.out.println("[SMS] " + message);
  }
}