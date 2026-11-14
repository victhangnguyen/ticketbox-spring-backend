// Path: src/main/java/com/ticketbox/springcoredemo/controller/EventController.java
package com.ticketbox.springcoredemo.controller;

import com.ticketbox.springcoredemo.dao.EventDAO;
import com.ticketbox.springcoredemo.entity.Event;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

  private final EventDAO eventDAO;

  // Constructor Injection: EventDAO la dependency bat buoc cua controller
  @Autowired
  public EventController(EventDAO eventDAO) {
    this.eventDAO = eventDAO;
  }

  // Endpoint TAM THOI de xac nhan cau truc Entity (chua luu database - xem Lesson
  // 03)
  @GetMapping("/api/events/sample")
  public Event getSampleEvent() {
    System.out.println("getSampleEvent() /api/events/sample");
    return new Event("Music Festival 2026", "TicketBox Organizer", 500000);
  }

  // Nhan JSON tu app di dong, luu xuong MySQL, tra ve event kem id that
  @PostMapping("/api/events")
  public Event createEvent(@RequestBody Event event) {
    eventDAO.save(event); // persist -> INSERT
    return event; // event luc nay da co id do MySQL sinh ra
  }

  // Endpoint moi: luu nhieu su kien, tra ve danh sach kem id do MySQL cap
  @PostMapping("/api/events/batch")
  public List<Event> createEvents(@RequestBody List<Event> events) {
    for (Event event : events) {
      eventDAO.save(event); // moi lan save() la mot transaction rieng
    }
    return events;
  }

  // C07L07-02: Lấy danh sách sự kiện nếu có tham số organizer thì lọc theo tên
  // nhà tổ chức
  @GetMapping("/api/events")
  public List<Event> getEvents(@RequestParam(required = false) String organizer) {
    if (organizer == null || organizer.isBlank()) {
      return eventDAO.findAll();
    }
    return eventDAO.findByOrganizerName(organizer);
  }

  // Endpoint moi: doc mot su kien theo khoa chinh
  // @GetMapping("/api/events/{id}")
  // public Event getEvent(@PathVariable int id) {
  // return eventDAO.findById(id);
  // }
  @GetMapping("/api/events/{id}")
  public ResponseEntity<Event> getEvent(@PathVariable int id) {
    Event event = eventDAO.findById(id);
    if (event == null) {
      return ResponseEntity.notFound().build(); // HTTP 404
    }
    return ResponseEntity.ok(event); // HTTP 200 + du lieu
  }

  // Tìm kiếm theo từ khóa. Đường dẫn có cố định "/search" được ưu tiên trước
  // "/{id}" để tránh nhầm lẫn với ID.
  @GetMapping("/api/events/search")
  public List<Event> searchEvents(@RequestParam String keyword) {
    return eventDAO.searchByKeyword(keyword);
  }

  // Cập nhật một sự kiện theo ID. Nếu sự kiện không tồn tại, trả về 404; nếu thành công, trả về 200 kèm dữ liệu mới.
  @PutMapping("/api/events/{id}")
  public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event updated) {
    Event existing = eventDAO.findById(id);
    if (existing == null) {
      return ResponseEntity.notFound().build();
    }

    // Chép giá trị mới vào đối tượng hiện có; id lấy từ đường dẫn, không lấy từ thân request
    existing.setEventName(updated.getEventName());
    existing.setOrganizerName(updated.getOrganizerName());
    existing.setTicketPrice(updated.getTicketPrice());

    //! Merge sẽ lưu các thay đổi xuống EntityManager và database; cần @Transactional trong DAO
    eventDAO.update(existing);
    return ResponseEntity.ok(existing);
  }

}
