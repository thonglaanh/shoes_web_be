package com.shoes.controller;

import com.shoes.dto.DanhGiaRequest;
import com.shoes.dto.DanhGiaResponse;
import com.shoes.dto.RatingStatsResponse;
import com.shoes.service.DanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class DanhGiaController {

    @Autowired
    private DanhGiaService service;

    @GetMapping("/product/{maSanPham}")
    public ResponseEntity<List<DanhGiaResponse>> getByProductId(@PathVariable Integer maSanPham) {
        try {
            return ResponseEntity.ok(service.getByProductId(maSanPham));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{maTaiKhoan}")
    public ResponseEntity<List<DanhGiaResponse>> getByUserId(@PathVariable Integer maTaiKhoan) {
        try {
            return ResponseEntity.ok(service.getByUserId(maTaiKhoan));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/stats/product/{maSanPham}")
    public ResponseEntity<?> getProductStats(@PathVariable Integer maSanPham) {
        try {
            RatingStatsResponse stats = service.getProductRatingStats(maSanPham);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DanhGiaRequest request) {
        try {
            DanhGiaResponse response = service.create(
                    request.getMaSanPham(),
                    request.getMaTaiKhoan(),
                    request.getSoSao(),
                    request.getComment());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{maDanhGia}")
    public ResponseEntity<?> update(@PathVariable Integer maDanhGia, @RequestBody DanhGiaRequest request) {
        try {
            DanhGiaResponse response = service.update(
                    maDanhGia,
                    request.getSoSao(),
                    request.getComment());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{maDanhGia}")
    public ResponseEntity<?> delete(@PathVariable Integer maDanhGia) {
        try {
            service.delete(maDanhGia);
            return ResponseEntity.ok("Xóa đánh giá thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
