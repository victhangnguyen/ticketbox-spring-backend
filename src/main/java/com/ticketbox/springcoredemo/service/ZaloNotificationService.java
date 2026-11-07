// Path: src/main/java/com/ticketbox/springcoredemo/service/ZaloNotificationService.java
package com.ticketbox.springcoredemo.service;

import org.springframework.stereotype.Component;

@Component
public class ZaloNotificationService implements NotificationService {

  public ZaloNotificationService() {
    System.out.println("In constructor: ZaloNotificationService");
  }

  @Override
  public void send(String message) {
    System.out.println("[Zalo] " + message);
  }
}
