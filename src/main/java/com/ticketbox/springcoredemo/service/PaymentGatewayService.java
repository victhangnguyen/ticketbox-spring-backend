// Path: src/main/java/com/ticketbox/springcoredemo/service/PaymentGatewayService.java
package com.ticketbox.springcoredemo.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component // Mac dinh singleton, dam bao @PreDestroy duoc goi khi Container dong
public class PaymentGatewayService {

  public PaymentGatewayService() {
    System.out.println("In constructor: PaymentGatewayService");
  }

  // Buoc 1 + 2: dinh nghia init method va gan @PostConstruct
  @PostConstruct
  public void openGatewaySession() {
    System.out.println("[PaymentGateway] Da mo phien ket noi cong thanh toan");
  }

  public String processPayment(String eventName, double amount) {
    return "Da xu ly thanh toan " + amount + " VND cho su kien: " + eventName;
  }

  // Buoc 1 + 2: dinh nghia destroy method va gan @PreDestroy
  @PreDestroy
  public void closeGatewaySession() {
    System.out.println("[PaymentGateway] Da dong phien ket noi cong thanh toan");
  }
}
