package com.shoes.controller;

import com.shoes.entity.GiamGia;
import com.shoes.service.GiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "*")
public class GiamGiaController {

    @Autowired
    private GiamGiaService service;

    @GetMapping
    public ResponseEntity<List<GiamGia>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/status/{trangThai}")
    public ResponseEntity<List<GiamGia>> getByTrangThai(@PathVariable Integer trangThai) {
        return ResponseEntity.ok(service.getByTrangThai(trangThai));
    }

    @GetMapping("/active")
    public ResponseEntity<List<GiamGia>> getActive() {
        return ResponseEntity.ok(service.getActive());
    }

    /**
     * Áp dụng mã giảm giá — trả về soTienGiam và soTienThanhToan
     * Body: { "maCode": "SUMMER20", "orderTotal": 500000 }
     */
    @PostMapping("/apply")
    public ResponseEntity<?> applyCode(@RequestBody Map<String, Object> body) {
        try {
            String maCode = (String) body.get("maCode");
            double orderTotal = ((Number) body.get("orderTotal")).doubleValue();
            return ResponseEntity.ok(service.applyCode(maCode, orderTotal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody GiamGia entity) {
        try {
            return ResponseEntity.ok(service.create(entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody GiamGia entity) {
        try {
            return ResponseEntity.ok(service.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Đã xóa phiếu giảm giá");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
