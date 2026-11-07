package com.ticketbox.springcoredemo.service;

// import jakarta.annotation.PostConstruct;
// import jakarta.annotation.PreDestroy;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
// Path: src/main/java/com/ticketbox/springcoredemo/service/EmailNotificationService.java
import org.springframework.stereotype.Component;

@Component // Dang ky class nay thanh Spring Bean / Register this class as a Spring Bean
@Primary // EmailNotificationService la kenh thong bao mac dinh cua he thong
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Tao instance moi cho moi lan tiem
public class EmailNotificationService implements NotificationService {

  public EmailNotificationService() {
    System.out.println("In constructor: EmailNotificationService");
  }

  // @PostConstruct
  // public void connectSmtpServer() {
  //   // Called right after the Bean is fully constructed
  //   // Duoc goi ngay sau khi Bean duoc khoi tao xong
  //   System.out.println("[Lifecycle] Da ket noi toi SMTP server");
  // }

  @Override
  public void send(String message) {
    System.out.println("[Email] " + message);
  }

  // @PreDestroy
  // public void closeSmtpConnection() {
  //   // Called right before the Container destroys the Bean
  //   // Duoc goi ngay truoc khi Container Spring huy Bean
  //   System.out.println("[Lifecycle] Da dong ket noi SMTP server");
  // }
}
