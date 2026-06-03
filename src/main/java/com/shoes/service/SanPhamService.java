package com.shoes.service;

import com.shoes.entity.SanPham;
import com.shoes.entity.ChatLieu;
import com.shoes.entity.ThuongHieu;
import com.shoes.entity.XuatXu;
import com.shoes.repository.SanPhamRepository;
import com.shoes.repository.ChatLieuRepository;
import com.shoes.repository.ThuongHieuRepository;
import com.shoes.repository.XuatXuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository repo;

    @Autowired
    private ChatLieuRepository chatLieuRepo;

    @Autowired
    private ThuongHieuRepository thuongHieuRepo;

    @Autowired
    private XuatXuRepository xuatXuRepo;

    public List<SanPham> getAll() {
        return repo.findAll();
    }

    public SanPham getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
    }

    public SanPham create(SanPham entity) {
        if (entity.getChatLieu() != null && entity.getChatLieu().getMaChatLieu() != null) {
            ChatLieu cl = chatLieuRepo.findById(entity.getChatLieu().getMaChatLieu())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy chất liệu"));
            entity.setChatLieu(cl);
        }
        if (entity.getThuongHieu() != null && entity.getThuongHieu().getMaThuongHieu() != null) {
            ThuongHieu th = thuongHieuRepo.findById(entity.getThuongHieu().getMaThuongHieu())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy thương hiệu"));
            entity.setThuongHieu(th);
        }
        if (entity.getXuatXu() != null && entity.getXuatXu().getMaXuatXu() != null) {
            XuatXu xx = xuatXuRepo.findById(entity.getXuatXu().getMaXuatXu())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy xuất xứ"));
            entity.setXuatXu(xx);
        }
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public SanPham update(Integer id, SanPham entity) {
        SanPham existing = getById(id);
        existing.setTenSanPham(entity.getTenSanPham());
        existing.setMoTa(entity.getMoTa());
        existing.setHinhAnh(entity.getHinhAnh());
        existing.setTrangThai(entity.getTrangThai());
        existing.setNguoiCapNhat(entity.getNguoiCapNhat());
        if (entity.getChatLieu() != null && entity.getChatLieu().getMaChatLieu() != null) {
            existing.setChatLieu(chatLieuRepo.findById(entity.getChatLieu().getMaChatLieu()).orElse(null));
        }
        if (entity.getThuongHieu() != null && entity.getThuongHieu().getMaThuongHieu() != null) {
            existing.setThuongHieu(thuongHieuRepo.findById(entity.getThuongHieu().getMaThuongHieu()).orElse(null));
        }
        if (entity.getXuatXu() != null && entity.getXuatXu().getMaXuatXu() != null) {
            existing.setXuatXu(xuatXuRepo.findById(entity.getXuatXu().getMaXuatXu()).orElse(null));
        }
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
