package com.shoes.service;

import com.shoes.entity.SanPhamChiTiet;
import com.shoes.entity.SanPham;
import com.shoes.entity.MauSac;
import com.shoes.entity.Size;
import com.shoes.repository.SanPhamChiTietRepository;
import com.shoes.repository.SanPhamRepository;
import com.shoes.repository.MauSacRepository;
import com.shoes.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository repo;

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @Autowired
    private MauSacRepository mauSacRepo;

    @Autowired
    private SizeRepository sizeRepo;

    public List<SanPhamChiTiet> getAll() {
        return repo.findAll();
    }

    public SanPhamChiTiet getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm chi tiết"));
    }

    public List<SanPhamChiTiet> getByProductId(Integer maSanPham) {
        return repo.findBySanPham_MaSanPham(maSanPham);
    }

    public SanPhamChiTiet create(SanPhamChiTiet entity) {
        if (entity.getSanPham() != null && entity.getSanPham().getMaSanPham() != null) {
            SanPham sp = sanPhamRepo.findById(entity.getSanPham().getMaSanPham())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
            entity.setSanPham(sp);
        }
        if (entity.getMauSac() != null && entity.getMauSac().getMaMauSac() != null) {
            MauSac ms = mauSacRepo.findById(entity.getMauSac().getMaMauSac())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy màu sắc"));
            entity.setMauSac(ms);
        }
        if (entity.getSize() != null && entity.getSize().getMaSize() != null) {
            Size sz = sizeRepo.findById(entity.getSize().getMaSize())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy size"));
            entity.setSize(sz);
        }
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public SanPhamChiTiet update(Integer id, SanPhamChiTiet entity) {
        SanPhamChiTiet existing = getById(id);
        existing.setSoLuong(entity.getSoLuong());
        existing.setGiaTien(entity.getGiaTien());
        existing.setTrangThai(entity.getTrangThai());
        existing.setHinhAnh(entity.getHinhAnh());
        if (entity.getSanPham() != null && entity.getSanPham().getMaSanPham() != null) {
            existing.setSanPham(sanPhamRepo.findById(entity.getSanPham().getMaSanPham()).orElse(null));
        }
        if (entity.getMauSac() != null && entity.getMauSac().getMaMauSac() != null) {
            existing.setMauSac(mauSacRepo.findById(entity.getMauSac().getMaMauSac()).orElse(null));
        }
        if (entity.getSize() != null && entity.getSize().getMaSize() != null) {
            existing.setSize(sizeRepo.findById(entity.getSize().getMaSize()).orElse(null));
        }
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
