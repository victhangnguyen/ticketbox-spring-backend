// Path: src/main/java/com/ticketbox/springcoredemo/dao/EventDAOImpl.java
package com.ticketbox.springcoredemo.dao;

import com.ticketbox.springcoredemo.entity.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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

  // Bước 2: JPQL "FROM Event" - Event là tên Entity (class), KHÔNG phải tên bảng
  // "event"
  // Chỉ đọc dữ liệu nên không cần @Transactional
  @Override
  public List<Event> findAll() {
    TypedQuery<Event> theQuery = entityManager.createQuery("FROM Event", Event.class);
    return theQuery.getResultList();
  }

}