package com.shoes.controller;

import com.shoes.entity.HoaDonChiTiet;
import com.shoes.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/invoice-details")
@CrossOrigin(origins = "*")
public class HoaDonChiTietController {

    @Autowired
    private HoaDonChiTietService service;

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

    @GetMapping("/by-invoice/{maHoaDon}")
    public ResponseEntity<?> getByHoaDon(@PathVariable Integer maHoaDon) {
        try {
            return ResponseEntity.ok(service.getByHoaDon(maHoaDon));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader("X-User-Id") Integer requesterId,
            @RequestBody HoaDonChiTiet entity) {
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
            @RequestBody HoaDonChiTiet entity) {
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
            return ResponseEntity.ok(Map.of("message", "Đã xóa chi tiết hóa đơn"));
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
