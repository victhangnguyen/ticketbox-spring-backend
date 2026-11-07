package com.ticketbox.springcoredemo.entity;

import org.hibernate.Hibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "event") // Buoc 1: anh xa class toi bang "event"
public class Event {

  // Buoc 2: anh xa tung field toi dung cot database
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "event_name")
  private String eventName;

  @Column(name = "organizer_name")
  private String organizerName;

  @Column(name = "ticket_price")
  private int ticketPrice;

  // Khi bạn tự khai báo constructor có tham số, Java sẽ không tự tạo constructor mặc định nữa.
  // Hibernate cần constructor không tham số để khởi tạo Entity khi đọc dữ liệu từ MySQL.
  public Event() {
  }

  // Constructor co tham so (khong bao gom id, vi id duoc database tu sinh)
  public Event(String eventName, String organizerName, int ticketPrice) {
    this.eventName = eventName;
    this.organizerName = organizerName;
    this.ticketPrice = ticketPrice;
  }

  // Getters va Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getOrganizerName() {
    return organizerName;
  }

  public void setOrganizerName(String organizerName) {
    this.organizerName = organizerName;
  }

  public int getTicketPrice() {
    return ticketPrice;
  }

  public void setTicketPrice(int ticketPrice) {
    this.ticketPrice = ticketPrice;
  }

  @Override
  public String toString() {
    return "Event{id=" + id + ", eventName='" + eventName + "', organizerName='"
        + organizerName + "', ticketPrice=" + ticketPrice + "}";
  }
}
