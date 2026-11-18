// Path: src/main/java/com/ticketbox/springcoredemo/controller/TicketController.java
package com.ticketbox.springcoredemo.controller;

import com.ticketbox.springcoredemo.dao.EventDAO;
import com.ticketbox.springcoredemo.entity.Event;
import com.ticketbox.springcoredemo.service.NotificationService;
import com.ticketbox.springcoredemo.service.PaymentGatewayService;
import com.ticketbox.springcoredemo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

  private final TicketService ticketService;
  private final PaymentGatewayService paymentGatewayService;
  // private final NotificationService myNotificationService;
  // private final NotificationService anotherNotificationService;

  // Constructor Injection: TicketController nhan TicketService tu Spring
  // Container
  // Buoc 3: Constructor Injection - TicketService la dependency bat buoc
  @Autowired
  public TicketController(TicketService ticketService,
      PaymentGatewayService paymentGatewayService) {
    // @Qualifier("emailNotificationService") NotificationService
    // myNotificationService,
    // @Qualifier("emailNotificationService") NotificationService
    // anotherNotificationService) {
    this.ticketService = ticketService;
    this.paymentGatewayService = paymentGatewayService;
    // this.myNotificationService = myNotificationService;
    // this.anotherNotificationService = anotherNotificationService;

    System.out.println("In constructor: TicketController");
  }

  // Buoc 4: Them endpoint su dung dependency da duoc tiem
  @GetMapping("/api/tickets/confirm")
  public String confirmPurchase(@RequestParam String eventName) {
    return ticketService.confirmPurchase(eventName);
  }

  @GetMapping("/api/tickets/pay")
  public String processPayment(@RequestParam("eventName") String eventName,
      @RequestParam("amount") double amount) {
    return paymentGatewayService.processPayment(eventName, amount);
  }
}