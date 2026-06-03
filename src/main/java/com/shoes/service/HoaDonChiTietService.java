package com.shoes.service;

import com.shoes.entity.HoaDon;
import com.shoes.entity.HoaDonChiTiet;
import com.shoes.entity.TaiKhoan;
import com.shoes.repository.HoaDonChiTietRepository;
import com.shoes.repository.HoaDonRepository;
import com.shoes.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository repo;

    @Autowired
    private HoaDonRepository hoaDonRepository;

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

    public List<HoaDonChiTiet> getAll() {
        return repo.findAll();
    }

    public HoaDonChiTiet getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết hóa đơn"));
    }

    public List<HoaDonChiTiet> getByHoaDon(Integer maHoaDon) {
        return repo.findByHoaDon_MaHoaDon(maHoaDon);
    }

    public HoaDonChiTiet create(Integer requesterId, HoaDonChiTiet entity) {
        checkPermission(requesterId, "ADMIN", "NHANVIEN");
        // Resolve HoaDon relationship
        if (entity.getHoaDon() != null && entity.getHoaDon().getMaHoaDon() != null) {
            HoaDon hd = hoaDonRepository.findById(entity.getHoaDon().getMaHoaDon())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));
            entity.setHoaDon(hd);
        }
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public HoaDonChiTiet update(Integer requesterId, Integer id, HoaDonChiTiet entity) {
        checkPermission(requesterId, "ADMIN", "NHANVIEN");
        HoaDonChiTiet existing = getById(id);
        if (entity.getSoLuong() != null)
            existing.setSoLuong(entity.getSoLuong());
        if (entity.getHinhAnh() != null)
            existing.setHinhAnh(entity.getHinhAnh());
        if (entity.getGiaTien() != null)
            existing.setGiaTien(entity.getGiaTien());
        if (entity.getSize() != null)
            existing.setSize(entity.getSize());
        if (entity.getMauSac() != null)
            existing.setMauSac(entity.getMauSac());
        if (entity.getTenSanPham() != null)
            existing.setTenSanPham(entity.getTenSanPham());
        if (entity.getMaSanPhamChiTiet() != null)
            existing.setMaSanPhamChiTiet(entity.getMaSanPhamChiTiet());
        if (entity.getHoaDon() != null && entity.getHoaDon().getMaHoaDon() != null) {
            HoaDon hd = hoaDonRepository.findById(entity.getHoaDon().getMaHoaDon())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));
            existing.setHoaDon(hd);
        }
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer requesterId, Integer id) {
        checkPermission(requesterId, "ADMIN");
        if (!repo.existsById(id)) {
            throw new RuntimeException("Không tìm thấy chi tiết hóa đơn");
        }
        repo.deleteById(id);
    }
}
