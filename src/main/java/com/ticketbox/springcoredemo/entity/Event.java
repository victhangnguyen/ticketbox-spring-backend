// Path: src/main/java/com/ticketbox/springcoredemo/entity/Event.java
package com.ticketbox.springcoredemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  // C03L10-02: Điều Chỉnh Câu Lệnh CREATE TABLE Bằng @Column(nullable, length)
  // nullable = false -> cột NOT NULL; length = 128 -> varchar(128) thay cho mặc
  // định 255
  @Column(name = "event_name", nullable = false, length = 128)
  private String eventName;

  @Column(name = "organizer_name", nullable = false, length = 128)
  private String organizerName;

  // Kiểu nguyên thủy int luôn là NOT NULL
  @Column(name = "ticket_price", nullable = false)
  private int ticketPrice;

  // Bắt buộc có constructor không tham số cho Entity
  public Event() {
  }

  public Event(String eventName, String organizerName, int ticketPrice) {
    this.eventName = eventName;
    this.organizerName = organizerName;
    this.ticketPrice = ticketPrice;
  }

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