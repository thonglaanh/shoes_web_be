package com.shoes.controller;

import com.shoes.entity.SanPhamChiTiet;
import com.shoes.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-details")
@CrossOrigin(origins = "*")
public class SanPhamChiTietController {

    @Autowired
    private SanPhamChiTietService service;

    @GetMapping
    public ResponseEntity<List<SanPhamChiTiet>> getAll() {
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

    @GetMapping("/by-product/{maSanPham}")
    public ResponseEntity<List<SanPhamChiTiet>> getByProduct(@PathVariable Integer maSanPham) {
        return ResponseEntity.ok(service.getByProductId(maSanPham));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SanPhamChiTiet entity) {
        try {
            return ResponseEntity.ok(service.create(entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SanPhamChiTiet entity) {
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
            return ResponseEntity.ok("Đã xóa sản phẩm chi tiết");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
