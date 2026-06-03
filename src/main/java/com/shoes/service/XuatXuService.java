package com.shoes.service;

import com.shoes.entity.XuatXu;
import com.shoes.repository.XuatXuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class XuatXuService {

    @Autowired
    private XuatXuRepository repo;

    public List<XuatXu> getAll() {
        return repo.findAll();
    }

    public XuatXu getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy xuất xứ"));
    }

    public XuatXu create(XuatXu entity) {
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public XuatXu update(Integer id, XuatXu entity) {
        XuatXu existing = getById(id);
        existing.setTenXuatXu(entity.getTenXuatXu());
        existing.setTrangThai(entity.getTrangThai());
        existing.setNguoiCapNhat(entity.getNguoiCapNhat());
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
