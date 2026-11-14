// // Path: src/main/java/com/ticketbox/springcoredemo/SpringcoredemoApplication.java
// package com.ticketbox.springcoredemo;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class SpringcoredemoApplication {

//   public static void main(String[] args) {
//     SpringApplication.run(SpringcoredemoApplication.class, args);
//   }

//   // CommandLineRunner cua Vi du 1 da duoc go bo de tranh luu trung du lieu moi
//   // lan khoi dong
// }

// ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
// 03L08-01 Querying Sửa Một Sự Kiện Bằng find → Setter → update

package com.ticketbox.springcoredemo;

import com.ticketbox.springcoredemo.dao.EventDAO;
import com.ticketbox.springcoredemo.entity.Event;
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
    return runner -> updateEvent(eventDAO);
  }

  // Buoc 3: find -> setter -> update -> hien thi ket qua
  private void updateEvent(EventDAO eventDAO) {
    int eventId = 2;

    // Retrieve the event by id / Lay su kien theo id
    System.out.println("Getting event with id: " + eventId);
    Event myEvent = eventDAO.findById(eventId);

    // Change values with setters / Doi gia tri bang setter
    System.out.println("Updating event...");
    myEvent.setEventName("Trien Lam Anh Quoc Te");
    myEvent.setTicketPrice(75000);

    // Save the change / Luu thay doi xuong database
    eventDAO.update(myEvent);

    // Display the updated event / Hien thi su kien sau khi cap nhat
    System.out.println("Updated event: " + myEvent);
  }
}
