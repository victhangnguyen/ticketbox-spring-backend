// Path: src/main/java/com/ticketbox/springcoredemo/SpringcoredemoApplication.java
package com.ticketbox.springcoredemo;

import com.ticketbox.springcoredemo.dao.EventDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringcoredemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringcoredemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(EventDAO eventDAO) {
    return runner -> deleteEvent(eventDAO);
  }

  // C03L09-01: Bước 3. Gọi DAO để xóa sự kiện có ID = 1
  private void deleteEvent(EventDAO eventDAO) {
    int eventId = 1;
    System.out.println("Deleting event id: " + eventId);
    eventDAO.delete(eventId);
  }
}
