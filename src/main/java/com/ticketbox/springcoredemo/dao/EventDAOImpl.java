// Path: src/main/java/com/ticketbox/springcoredemo/dao/EventDAOImpl.java
package com.ticketbox.springcoredemo.dao;

import com.ticketbox.springcoredemo.entity.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // Sub-annotation cua @Component: ho tro component scanning + dich ngoai le JDBC
public class EventDAOImpl implements EventDAO {

  // Field giu EntityManager do Spring Boot tu tao
  private final EntityManager entityManager;

  // Buoc 2: tiem EntityManager qua constructor
  @Autowired
  public EventDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  // Spring tu mo/dong transaction cho phuong thuc ghi du lieu nay
  @Override
  @Transactional
  public void save(Event event) {
    entityManager.persist(event); // JPA/Hibernate tu sinh cau lenh INSERT
  }

  @Override
  public Event findById(int id) {
    // trả về null nếu không có dòng nào khớp
    return entityManager.find(Event.class, id);
  }

  // JPQL "FROM Event" - Event là tên Entity (class), KHÔNG phải tên bảng
  // "event"
  // Chỉ đọc dữ liệu nên không cần @Transactional
  @Override
  public List<Event> findAll() {
    TypedQuery<Event> theQuery = entityManager.createQuery("FROM Event", Event.class);
    return theQuery.getResultList();
  }

  // JPQL "FROM Event WHERE organizerName = :theData" - Event là tên Entity
  // (class), KHÔNG phải tên bảng "event"
  // Chỉ đọc dữ liệu nên không cần @Transactional
  @Override
  public List<Event> findByOrganizerName(String organizerName) {
    // Create query with a named parameter / Tao query voi tham so co ten
    TypedQuery<Event> theQuery = entityManager.createQuery(
        "FROM Event WHERE organizerName = :theData", Event.class);

    // Set the parameter value / Dien gia tri cho tham so (ten KHONG kem dau ":")
    theQuery.setParameter("theData", organizerName);

    // Return the query results / Tra ve ket qua
    return theQuery.getResultList();
  }

  @Override
  public List<Event> searchByKeyword(String keyword) {
    // ! Một tham số có tên ":kw" được sử dụng ở cả hai điều kiện OR
    TypedQuery<Event> theQuery = entityManager.createQuery(
        "FROM Event WHERE eventName LIKE :kw OR organizerName LIKE :kw", Event.class);

    // ! Ký tự % được thêm vào giá trị tham số để khớp một phần chuỗi ở bất kỳ vị
    // trí nào
    theQuery.setParameter("kw", "%" + keyword + "%");

    return theQuery.getResultList(); // Danh sách rỗng nếu không có dòng nào khớp
  }

  // Thao tác ghi dữ liệu nên bắt buộc phải có @Transactional; merge sẽ lưu các
  // thay đổi xuống database
  @Override
  @Transactional
  public void update(Event event) {
    entityManager.merge(event);
  }

  // C03L08-03: Đổi giá vé hàng loạt theo nhà tổ chức, trả về số dòng đã cập nhật
  @Override
  @Transactional
  public int updateTicketPriceByOrganizer(String organizerName, int newPrice) {
    // Event là tên Entity (class), ticketPrice/organizerName là tên field Java,
    // KHÔNG phải tên cột trong bảng
    // Dùng Query thường (không phải TypedQuery) vì UPDATE không trả về danh sách
    // Event
    Query theQuery = entityManager.createQuery(
        "UPDATE Event SET ticketPrice = :newPrice WHERE organizerName = :org");
    theQuery.setParameter("newPrice", newPrice);
    theQuery.setParameter("org", organizerName);

    System.out.println("updateTicketPriceByOrganizer: " + theQuery.executeUpdate());
    return theQuery.executeUpdate(); // số dòng đã cập nhật (bị ảnh hưởng)
  }

  // C03L09-01: Bước 2: Thao tác XÓA Sư kiện, thay đổi dữ liệu nên BẮT BUỘC phải
  // có @Transactional
  @Override
  @Transactional
  public void delete(Integer id) {
    // Retrieve the event / Lấy ự kiện theo ID (theEvent có thể null nếu không tìm
    // thấy)
    Event theEvent = entityManager.find(Event.class, id);

    // Delete the event / Xóa sự kiện (theEvent phải khác null)
    entityManager.remove(theEvent);
  }

  // C03L09-03: Bước 2: Định nghĩa deleteByOrganizerName
  @Override
  // Thao tác Xóa nên bắt buộc có @Transactional
  @Transactional
  public int deleteByOrganizerName(String organizerName) {
    // "Event" Entity,
    // "organizerName" là tên field trong Event
    Query theQuery = entityManager.createQuery(
        "DELETE FROM Event WHERE organizerName = :org");
    theQuery.setParameter("org", organizerName);

    return theQuery.executeUpdate(); // so dong bi xoa
  }

  // C03L09-04: Bước 2: Định nghĩa deleteAll()
  @Override
  @Transactional
  public int deleteAll() {
    // Không có WHERE nên xóa TOÀN BỘ dòng của Bảng Event
    int numRowsDeleted = entityManager.createQuery("DELETE FROM Event").executeUpdate();
    return numRowsDeleted;
  }

}