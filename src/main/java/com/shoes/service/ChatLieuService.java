package com.shoes.service;

import com.shoes.entity.ChatLieu;
import com.shoes.repository.ChatLieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatLieuService {

    @Autowired
    private ChatLieuRepository repo;

    public List<ChatLieu> getAll() {
        return repo.findAll();
    }

    public ChatLieu getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy chất liệu"));
    }

    public ChatLieu create(ChatLieu entity) {
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public ChatLieu update(Integer id, ChatLieu entity) {
        ChatLieu existing = getById(id);
        existing.setTenChatLieu(entity.getTenChatLieu());
        existing.setTrangThai(entity.getTrangThai());
        existing.setNguoiCapNhat(entity.getNguoiCapNhat());
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
