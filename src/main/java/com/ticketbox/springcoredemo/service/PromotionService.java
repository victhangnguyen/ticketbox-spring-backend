package com.ticketbox.springcoredemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PromotionService {

  private final NotificationService notificationService;

  // @Qualifier co do uu tien cao hon @Primary tren EmailNotificationService
  @Autowired
  public PromotionService(
      @Qualifier("zaloNotificationService") NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  public void sendPromotion(String promoCode) {
    notificationService.send("Ma khuyen mai danh cho ban: " + promoCode);
  }
}
