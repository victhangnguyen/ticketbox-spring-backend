// Path: src/main/java/com/ticketbox/springcoredemo/SpringcoredemoApplication.java
package com.ticketbox.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcoredemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringcoredemoApplication.class, args);
  }

  // CommandLineRunner cua Vi du 1 da duoc go bo de tranh luu trung du lieu moi
  // lan khoi dong
}

// ---- ---- ---- ---- ---- ---- ---- ---- ---- ----
// Example_01_VSCode_Spring_V3_C03L07_Querying Objects with JPA_26-10-26
// package com.ticketbox.springcoredemo;

// import com.ticketbox.springcoredemo.dao.EventDAO;
// import com.ticketbox.springcoredemo.entity.Event;
// import java.util.List;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// @SpringBootApplication
// public class SpringcoredemoApplication {

// public static void main(String[] args) {
// SpringApplication.run(SpringcoredemoApplication.class, args);
// }

// @Bean
// public CommandLineRunner commandLineRunner(EventDAO eventDAO) {
// return runner -> queryForEvents(eventDAO);
// }

// // Buoc 3: lay danh sach su kien va hien thi tung su kien
// private void queryForEvents(EventDAO eventDAO) {
// // Get list of events / Lay danh sach su kien
// List<Event> theEvents = eventDAO.findAll();

// // Display list of events / Hien thi danh sach
// for (Event tempEvent : theEvents) {
// System.out.println(tempEvent);
// }
// }
// }

// ---- ---- ---- ---- ---- ---- ---- ---- ---- ----

// // Path:
// src/main/java/com/ticketbox/springcoredemo/SpringcoredemoApplication.java
// package com.ticketbox.springcoredemo;

// import com.ticketbox.springcoredemo.dao.EventDAO;
// import com.ticketbox.springcoredemo.entity.Event;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// @SpringBootApplication
// public class SpringcoredemoApplication {

// public static void main(String[] args) {
// SpringApplication.run(SpringcoredemoApplication.class, args);
// }

// @Bean
// public CommandLineRunner commandLineRunner(EventDAO eventDAO) {
// return runner -> readEvent(eventDAO);
// }

// // Buoc 3: luu mot su kien, doc lai theo id va hien thi
// private void readEvent(EventDAO eventDAO) {
// // Create the event object / Tao doi tuong su kien
// System.out.println("Creating new event object...");
// Event currentEvent = new Event("Dem Nhac Jazz", "Jazz Club", 350000);

// // Save the event / Luu su kien
// System.out.println("Saving the event...");
// eventDAO.save(currentEvent);

// // Display the saved id / Hien thi id da duoc MySQL cap
// int theId = currentEvent.getId();
// System.out.println("Saved event. Generated id: " + theId);

// // Retrieve the event by id / Doc lai su kien theo id
// System.out.println("Retrieving event with id: " + theId);
// Event myEvent = eventDAO.findById(theId);

// // Display the event / Hien thi su kien doc duoc
// System.out.println("Found the event: " + myEvent);
// }
// }
