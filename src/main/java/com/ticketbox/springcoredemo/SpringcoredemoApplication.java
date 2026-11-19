// Path: src/main/java/com/ticketbox/springcoredemo/SpringcoredemoApplication.java
package com.ticketbox.springcoredemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ticketbox.springcoredemo.dao.EventDAO;

@SpringBootApplication
public class SpringcoredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcoredemoApplication.class, args);
    }

    // C03L10-02: Xóa lệnh Giả lập - Điều Chỉnh Câu Lệnh CREATE TABLE Bằng @Column(nullable, length)
}
