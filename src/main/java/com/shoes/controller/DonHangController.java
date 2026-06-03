package com.shoes.controller;

import com.shoes.entity.DonHang;
import com.shoes.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class DonHangController {

    @Autowired
    private DonHangService service;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestHeader(value = "X-User-Id", required = false) Integer requesterId) {
        try {
            if (requesterId != null) {
                return ResponseEntity.ok(service.getAll(requesterId));
            }
            // Fallback: return all (public access for now)
            return ResponseEntity.ok(service.getAll(null));
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

    @GetMapping("/customer/{maKhachHang}")
    public ResponseEntity<?> getByCustomer(@PathVariable Integer maKhachHang) {
        try {
            return ResponseEntity.ok(service.getByCustomer(maKhachHang));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        try {
            Map<String, Long> stats = new HashMap<>();
            List<String> statuses = List.of("CHO_XAC_NHAN", "DA_XAC_NHAN", "DANG_GIAO", "HOAN_THANH", "DA_HUY");
            for (String s : statuses) {
                stats.put(s, (long) service.getByStatus(s).size());
            }
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DonHang entity) {
        try {
            return ResponseEntity.ok(service.create(entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestHeader("X-User-Id") Integer requesterId,
            @PathVariable Integer id,
            @RequestBody DonHang entity) {
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
            return ResponseEntity.ok(Map.of("message", "Đã xóa đơn hàng"));
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
