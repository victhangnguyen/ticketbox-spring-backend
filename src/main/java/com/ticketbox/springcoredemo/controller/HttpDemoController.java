// Path: src/main/java/com/ticketbox/springcoredemo/controller/HttpDemoController.java
package com.ticketbox.springcoredemo.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// C04L01-02: Một Backend Trả Về Nhiều MIME Type Và Cách Client Báo Định Dạng Mong Muốn

@RestController
@RequestMapping("/api/http-demo") // Tiền tố chung cho mọi endpoint trong class này
public class HttpDemoController {

    // MIME Type: text/plain — văn bản thuần
    @GetMapping(value = "/text", produces = MediaType.TEXT_PLAIN_VALUE)
    public String text() {
        return "Xin chao TicketBox";
    }

    // MIME Type: text/html — trình duyệt sẽ hiển thị theo thẻ HTML
    @GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
    public String html() {
        return "<h1>Xin chao TicketBox</h1>";
    }

    // MIME Type: application/json — dữ liệu để ứng dụng xử lý
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> json() {
        Map<String, Object> data = new LinkedHashMap<>(); // giữ thứ tự thêm vào
        data.put("app", "TicketBox");
        data.put("version", 1);
        data.put("active", true);
        data.put("venue", null);
        return data;
    }

    // Đọc header của yêu cầu mà client đã gửi lên
    @GetMapping("/headers")
    public Map<String, String> headers(
            @RequestHeader(value = "User-Agent", required = false) String userAgent,
            @RequestHeader(value = "Accept", required = false) String accept) {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("userAgent", userAgent);
        data.put("accept", accept);
        return data;
    }

    // Trả về đúng mã trạng thái mà client yêu cầu để học sinh quan sát
    @GetMapping("/status/{code}")
    public ResponseEntity<Map<String, Object>> status(@PathVariable int code) {
        Map<String, Object> data = new LinkedHashMap<>();
        HttpStatus status = HttpStatus.resolve(code);
        if (status == null) {
            data.put("error", "Ma trang thai khong hop le: " + code);
            return ResponseEntity.badRequest().body(data); // 400
        }
        data.put("status", code);
        data.put("reason", status.getReasonPhrase());
        return ResponseEntity.status(status).body(data);
    }
}