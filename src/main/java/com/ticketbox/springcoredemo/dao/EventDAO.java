// Path: src/main/java/com/ticketbox/springcoredemo/dao/EventDAO.java
package com.ticketbox.springcoredemo.dao;

import java.util.List;

import com.ticketbox.springcoredemo.entity.Event;

// Buoc 1: DAO interface - dinh nghia "hop dong" thao tac du lieu cho Event
public interface EventDAO {

  // Save a new event / Luu mot su kien moi
  void save(Event event);

  // ! Thêm phương thức Đọc theo Khóa Chính (Trả về null nếu không tìm thấy)
  Event findById(int id);

  // Thêm phương thức Lấy toàn bộ danh sách Sự kiện
  List<Event> findAll();

  // Thêm phương thức Lấy danh sách Sự kiện theo Tên Tổ chức
  List<Event> findByOrganizerName(String organizerName);

  // Tìm theo từ khóa trong tên sự kiện hoặc tên nhà tổ chức
  List<Event> searchByKeyword(String keyword);

  // Thêm phương thức Cập nhật một sự kiện
  void update(Event event);

  // C03L08-03: Đổi giá vé hàng loạt theo nhà tổ chức, trả về số dòng đã cập nhật
  int updateTicketPriceByOrganizer(String organizerName, int newPrice);

  // C03L09-01: Bước 1. Thêm phương thức Xóa một sự kiện theo ID
  void delete(Integer id);

  // C03L09-03: Bước 1. Xóa hàng loạt theo Nhà Tổ Chức, trả về số dòng đã Xóa
  int deleteByOrganizerName(String organizerName);

}
