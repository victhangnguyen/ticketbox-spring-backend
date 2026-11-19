// Path: src/main/java/com/ticketbox/springcoredemo/controller/EventController.java
package com.ticketbox.springcoredemo.controller;

import com.ticketbox.springcoredemo.dao.EventDAO;
import com.ticketbox.springcoredemo.entity.Event;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  public ResponseEntity<Event> createEvent(@RequestBody Event event) {
    eventDAO.save(event); // persist -> INSERT
    // return event; // event luc nay da co id do MySQL sinh ra
    // ! CREATE: tra ve HTTP 201 + du lieu moi tao
    return ResponseEntity.status(HttpStatus.CREATED).body(event);
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

  // C04L01-01: Trả 200 kèm JSON nếu tìm thấy, 404 nếu không có sự kiện với mã này
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

  // Cập nhật một sự kiện theo ID. Nếu sự kiện không tồn tại, trả về 404; nếu
  // thành công, trả về 200 kèm dữ liệu mới.
  @PutMapping("/api/events/{id}")
  public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event updated) {
    Event existing = eventDAO.findById(id);
    if (existing == null) {
      return ResponseEntity.notFound().build();
    }

    // Chép giá trị mới vào đối tượng hiện có; id lấy từ đường dẫn, không lấy từ
    // thân request
    existing.setEventName(updated.getEventName());
    existing.setOrganizerName(updated.getOrganizerName());
    existing.setTicketPrice(updated.getTicketPrice());

    // ! Merge sẽ lưu các thay đổi xuống EntityManager và database; cần
    // @Transactional trong DAO
    eventDAO.update(existing);
    System.out.println("ResponseEntity.ok(existing): " + ResponseEntity.ok(existing));
    return ResponseEntity.ok(existing);
  }

  // C03L08-03: Đổi giá hàng loạt. Đường dẫn cố định "/price" được ưu tiên trước
  // "/{id}" để tránh nhầm lẫn với ID.
  @PutMapping("/api/events/price")
  public Map<String, Integer> updatePriceByOrganizer(@RequestParam String organizer,
      @RequestParam int newPrice) {
    int rows = eventDAO.updateTicketPriceByOrganizer(organizer, newPrice);
    return Map.of("updatedRows", rows);
  }

  // C03L09-02: Bước 3: Controller - Xóa một Event.
  // C03L09-03: Bước 3. Controller - Xóa một Event.
  // 404 nếu không tồn tại, 204 nếu đã xóa
  @DeleteMapping("/api/events/{id}")
  public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
    if (eventDAO.findById(id) == null) {
      // Không có gì để xóa
      return ResponseEntity.notFound().build();
    }
    // find + remove trong transaction
    eventDAO.delete(id);
    return ResponseEntity.noContent().build(); // HTTP 204
  }

  // Endpoint tam thoi de xac nhan Entity anh xa dung cau truc (CHUA luu database)
  @GetMapping("/api/events/preview")
  public String previewEvent() {
    Event event = new Event("Music Festival 2026", "TicketBox Organizer", 500000);
    return event.toString();
  }

  // C03L09-03: Bước 3: Controller - Xóa hàng loạt Event dựa trên Organizer
  // Tham số "organizer" là BẮT BUỘC
  @DeleteMapping("/api/events")
  public Map<String, Integer> deleteByOrganizer(@RequestParam String organizer) {
    int rows = eventDAO.deleteByOrganizerName(organizer);
    return Map.of("deletedRows", rows);
  }

  // C03L09-04: Bước 3: Controller - Xóa TOÀN BỘ Events
  @DeleteMapping("/api/events/all")
  public Map<String, Integer> deleteAllEvents() {
    int numRowsDeleted = eventDAO.deleteAll();
    return Map.of("deletedRows", numRowsDeleted);
  }
}
