package com.shoes.service;

import com.shoes.entity.ThuongHieu;
import com.shoes.repository.ThuongHieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThuongHieuService {

    @Autowired
    private ThuongHieuRepository repo;

    public List<ThuongHieu> getAll() {
        return repo.findAll();
    }

    public ThuongHieu getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thương hiệu"));
    }

    public ThuongHieu create(ThuongHieu entity) {
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public ThuongHieu update(Integer id, ThuongHieu entity) {
        ThuongHieu existing = getById(id);
        existing.setTenThuongHieu(entity.getTenThuongHieu());
        existing.setTrangThai(entity.getTrangThai());
        existing.setNguoiCapNhat(entity.getNguoiCapNhat());
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
