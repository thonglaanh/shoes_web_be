package com.shoes.controller;

import com.shoes.dto.LoginRequest;
import com.shoes.dto.RegisterRequest;
import com.shoes.dto.TaiKhoanResponse;
import com.shoes.entity.TaiKhoan;
import com.shoes.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        try {
            TaiKhoanResponse res = taiKhoanService.register(req);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            TaiKhoanResponse res = taiKhoanService.login(req);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        try {
            TaiKhoanResponse res = taiKhoanService.getUser(id);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Integer id, @RequestBody TaiKhoan updated) {
        try {
            TaiKhoanResponse res = taiKhoanService.updateProfile(id, updated);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse(e.getMessage()));
        }
    }

    @PutMapping("/user/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        try {
            String oldPassword = body.get("oldPassword");
            String newPassword = body.get("newPassword");
            TaiKhoanResponse res = taiKhoanService.changePassword(id, oldPassword, newPassword);
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
