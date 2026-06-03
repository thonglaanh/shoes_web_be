package com.shoes.controller;

import com.shoes.dto.TaiKhoanResponse;
import com.shoes.entity.TaiKhoan;
import com.shoes.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService service;

    /**
     * Lấy danh sách nhân viên (ADMIN & NHANVIEN)
     */
    @GetMapping
    public ResponseEntity<?> getEmployees(@RequestHeader("X-User-Id") Integer requesterId) {
        try {
            List<TaiKhoanResponse> list = service.getEmployees(requesterId);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    /**
     * Lấy tất cả tài khoản (ADMIN only)
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts(@RequestHeader("X-User-Id") Integer requesterId) {
        try {
            List<TaiKhoanResponse> list = service.getAllAccounts(requesterId);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    /**
     * Lấy thông tin 1 nhân viên
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            TaiKhoanResponse res = service.getUser(id);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    /**
     * Tạo nhân viên (ADMIN only)
     */
    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader("X-User-Id") Integer requesterId,
            @RequestBody TaiKhoan employee) {
        try {
            TaiKhoanResponse res = service.createEmployee(requesterId, employee);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    /**
     * Cập nhật nhân viên (ADMIN only)
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestHeader("X-User-Id") Integer requesterId,
            @PathVariable Integer id,
            @RequestBody TaiKhoan employee) {
        try {
            TaiKhoanResponse res = service.updateEmployee(requesterId, id, employee);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    /**
     * Xóa nhân viên (ADMIN only)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @RequestHeader("X-User-Id") Integer requesterId,
            @PathVariable Integer id) {
        try {
            service.deleteEmployee(requesterId, id);
            return ResponseEntity.ok(Map.of("message", "Đã xóa nhân viên"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    /**
     * Lấy danh sách khách hàng (ADMIN & NHANVIEN)
     */
    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers(@RequestHeader("X-User-Id") Integer requesterId) {
        try {
            List<TaiKhoanResponse> list = service.getCustomers(requesterId);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    /**
     * Cập nhật khách hàng (ADMIN only)
     */
    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(
            @RequestHeader("X-User-Id") Integer requesterId,
            @PathVariable Integer id,
            @RequestBody TaiKhoan customer) {
        try {
            TaiKhoanResponse res = service.updateCustomer(requesterId, id, customer);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    /**
     * Khóa / Mở khóa khách hàng (ADMIN only)
     */
    @PatchMapping("/customers/{id}/toggle-status")
    public ResponseEntity<?> toggleCustomerStatus(
            @RequestHeader("X-User-Id") Integer requesterId,
            @PathVariable Integer id) {
        try {
            TaiKhoanResponse res = service.toggleCustomerStatus(requesterId, id);
            return ResponseEntity.ok(res);
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
