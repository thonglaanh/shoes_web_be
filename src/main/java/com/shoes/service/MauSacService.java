package com.shoes.service;

import com.shoes.entity.MauSac;
import com.shoes.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MauSacService {

    @Autowired
    private MauSacRepository repo;

    public List<MauSac> getAll() {
        return repo.findAll();
    }

    public MauSac getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy màu sắc"));
    }

    public MauSac create(MauSac entity) {
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public MauSac update(Integer id, MauSac entity) {
        MauSac existing = getById(id);
        existing.setTenMau(entity.getTenMau());
        existing.setTrangThai(entity.getTrangThai());
        existing.setNguoiCapNhat(entity.getNguoiCapNhat());
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
