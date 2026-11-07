package com.ticketbox.springcoredemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Path: src/main/java/com/ticketbox/springcoredemo/service/TicketService.java
@Component // TicketService nay cung la mot Spring Bean
public class TicketService {

  // Dependency will be provided by the Spring Container in Lesson 02–03
  // Dependency sẽ được Spring Container cung cấp ở Lesson 02–03
  private final NotificationService notificationService;
  // private final DiscountCalculator discountCalculator; // Dependency moi
  private LoggingService loggingService; // Tuy chon - Setter Injection

  // Constructor Injection: NotificationService la dependency bat buoc
  // Buoc 3: Constructor Injection - NotificationService la dependency bat buoc
  @Autowired
  // Khong con can @Qualifier vi da co @Primary tren EmailNotificationService
  public TicketService(NotificationService notificationService) {
    this.notificationService = notificationService;

    System.out.println("In constructor: NotificationService");
  }

  // @Autowired
  // public TicketService(NotificationService notificationService,
  // DiscountCalculator discountCalculator) {
  // this.notificationService = notificationService;
  // this.discountCalculator = discountCalculator;
  // }

  // Buoc 1: tao setter method
  // Buoc 2: cau hinh voi @Autowired
  @Autowired
  public void setLoggingService(LoggingService loggingService) {
    this.loggingService = loggingService;
  }

  public String confirmPurchase(String eventName) {
    String message = "Ban da mua ve thanh cong cho su kien: " + eventName;
    notificationService.send(message);

    if (loggingService != null) {
      loggingService.log(eventName); // Chi ghi log neu co san
    }

    return message;
  }
}
