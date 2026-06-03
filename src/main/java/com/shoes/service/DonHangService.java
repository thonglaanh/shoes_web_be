package com.shoes.service;

import com.shoes.entity.DonHang;
import com.shoes.entity.TaiKhoan;
import com.shoes.repository.DonHangRepository;
import com.shoes.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonHangService {

    @Autowired
    private DonHangRepository repo;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private GiamGiaService giamGiaService;

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

    public List<DonHang> getAll(Integer requesterId) {
        if (requesterId != null) {
            checkPermission(requesterId, "ADMIN", "NHANVIEN");
        }
        return repo.findAll();
    }

    public DonHang getById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
    }

    public List<DonHang> getByCustomer(Integer maKhachHang) {
        return repo.findByMaKhachHang(maKhachHang);
    }

    public List<DonHang> getByStatus(String trangThai) {
        return repo.findByTrangThai(trangThai);
    }

    public DonHang create(DonHang entity) {
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        if (entity.getTrangThai() == null) {
            entity.setTrangThai("CHO_XAC_NHAN");
        }
        DonHang saved = repo.save(entity);
        // Trừ lượt dùng mã giảm giá nếu có
        if (saved.getMaGiamGia() != null && !saved.getMaGiamGia().isBlank()) {
            try {
                giamGiaService.consumeCode(saved.getMaGiamGia());
            } catch (Exception ignored) {
            }
        }
        return saved;
    }

    public DonHang update(Integer requesterId, Integer id, DonHang entity) {
        checkPermission(requesterId, "ADMIN", "NHANVIEN");
        DonHang existing = getById(id);
        if (entity.getTenKhachHang() != null)
            existing.setTenKhachHang(entity.getTenKhachHang());
        if (entity.getSoDienThoai() != null)
            existing.setSoDienThoai(entity.getSoDienThoai());
        if (entity.getDiaChi() != null)
            existing.setDiaChi(entity.getDiaChi());
        if (entity.getTinhThanhPho() != null)
            existing.setTinhThanhPho(entity.getTinhThanhPho());
        if (entity.getQuanHuyen() != null)
            existing.setQuanHuyen(entity.getQuanHuyen());
        if (entity.getPhuongXa() != null)
            existing.setPhuongXa(entity.getPhuongXa());
        if (entity.getDiaChiCuThe() != null)
            existing.setDiaChiCuThe(entity.getDiaChiCuThe());
        if (entity.getEmail() != null)
            existing.setEmail(entity.getEmail());
        if (entity.getGhiChu() != null)
            existing.setGhiChu(entity.getGhiChu());
        if (entity.getSoTienBanDau() != null)
            existing.setSoTienBanDau(entity.getSoTienBanDau());
        if (entity.getSoTienDuocGiam() != null)
            existing.setSoTienDuocGiam(entity.getSoTienDuocGiam());
        if (entity.getSoTienThanhToan() != null)
            existing.setSoTienThanhToan(entity.getSoTienThanhToan());
        if (entity.getTrangThai() != null)
            existing.setTrangThai(entity.getTrangThai());
        if (entity.getMaGiamGia() != null)
            existing.setMaGiamGia(entity.getMaGiamGia());
        if (entity.getPttt() != null)
            existing.setPttt(entity.getPttt());
        if (entity.getMaNhanVien() != null)
            existing.setMaNhanVien(entity.getMaNhanVien());
        if (entity.getNguoiCapNhat() != null)
            existing.setNguoiCapNhat(entity.getNguoiCapNhat());
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer requesterId, Integer id) {
        checkPermission(requesterId, "ADMIN");
        if (!repo.existsById(id)) {
            throw new RuntimeException("Không tìm thấy đơn hàng");
        }
        repo.deleteById(id);
    }
}
