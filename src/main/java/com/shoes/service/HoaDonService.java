package com.shoes.service;

import com.shoes.entity.HoaDon;
import com.shoes.entity.TaiKhoan;
import com.shoes.repository.HoaDonRepository;
import com.shoes.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository repo;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    private void checkPermission(Integer requesterId, String... allowedRoles) {
        TaiKhoan requester = taiKhoanRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản người yêu cầu"));
        boolean allowed = false;
        for (String role : allowedRoles) {
            if (role.equals(requester.getLoaiTaiKhoan())) {
                allowed = true;
                break;
            }
        }
        if (!allowed) {
            throw new RuntimeException("Bạn không có quyền thực hiện thao tác này");
        }
    }

    public List<HoaDon> getAll() {
        return repo.findAll();
    }

    public HoaDon getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));
    }

    public HoaDon create(Integer requesterId, HoaDon entity) {
        checkPermission(requesterId, "ADMIN", "NHANVIEN");
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        if (entity.getTrangThai() == null) {
            entity.setTrangThai("ACTIVE");
        }
        return repo.save(entity);
    }

    public HoaDon update(Integer requesterId, Integer id, HoaDon entity) {
        checkPermission(requesterId, "ADMIN", "NHANVIEN");
        HoaDon existing = getById(id);
        if (entity.getTieuDe() != null)
            existing.setTieuDe(entity.getTieuDe());
        if (entity.getNoiDung() != null)
            existing.setNoiDung(entity.getNoiDung());
        if (entity.getHinhAnh() != null)
            existing.setHinhAnh(entity.getHinhAnh());
        if (entity.getTrangThai() != null)
            existing.setTrangThai(entity.getTrangThai());
        if (entity.getNguoiCapNhat() != null)
            existing.setNguoiCapNhat(entity.getNguoiCapNhat());
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer requesterId, Integer id) {
        checkPermission(requesterId, "ADMIN");
        if (!repo.existsById(id)) {
            throw new RuntimeException("Không tìm thấy hóa đơn");
        }
        repo.deleteById(id);
    }
}
