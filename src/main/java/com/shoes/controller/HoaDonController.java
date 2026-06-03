package com.shoes.controller;

import com.shoes.entity.HoaDon;
import com.shoes.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class HoaDonController {

    @Autowired
    private HoaDonService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader("X-User-Id") Integer requesterId,
            @RequestBody HoaDon entity) {
        try {
            return ResponseEntity.ok(service.create(requesterId, entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestHeader("X-User-Id") Integer requesterId,
            @PathVariable Integer id,
            @RequestBody HoaDon entity) {
        try {
            return ResponseEntity.ok(service.update(requesterId, id, entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @RequestHeader("X-User-Id") Integer requesterId,
            @PathVariable Integer id) {
        try {
            service.delete(requesterId, id);
            return ResponseEntity.ok(Map.of("message", "Đã xóa hóa đơn"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    private Map<String, String> errorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return error;
    }
}
