// Path: src/main/java/com/ticketbox/springcoredemo/SpringcoredemoApplication.java
package com.ticketbox.springcoredemo;

import com.ticketbox.springcoredemo.dao.EventDAO;
import com.ticketbox.springcoredemo.entity.Event;

import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringcoredemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringcoredemoApplication.class, args);
  }

  // C03L10-01: Bước 2: Bật ddl-auto=create Và Quan Sát Hibernate Xóa Rồi Tạo Lại
  // Bảng event
  // Lưu một sự kiện ngay sau khi bảng được tạo lại để chứng minh bảng mới hoạt
  // động
  @Bean
  public CommandLineRunner commandLineRunner(EventDAO eventDAO) {
    return runner -> {
      Event tempEvent = new Event("Music Festival 2026", "TicketBox Organizer", 500000);
      eventDAO.save(tempEvent);
      System.out.println("Da luu su kien moi voi id = " + tempEvent.getId());
    };
  }
}
