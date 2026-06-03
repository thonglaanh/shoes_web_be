package com.shoes.service;

import com.shoes.entity.Size;
import com.shoes.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SizeService {

    @Autowired
    private SizeRepository repo;

    public List<Size> getAll() {
        return repo.findAll();
    }

    public Size getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy size"));
    }

    public Size create(Size entity) {
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public Size update(Integer id, Size entity) {
        Size existing = getById(id);
        existing.setTenSize(entity.getTenSize());
        existing.setTrangThai(entity.getTrangThai());
        existing.setNguoiCapNhat(entity.getNguoiCapNhat());
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
