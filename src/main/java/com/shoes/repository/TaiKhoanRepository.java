package com.shoes.repository;

import com.shoes.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    Optional<TaiKhoan> findByEmail(String email);

    List<TaiKhoan> findByLoaiTaiKhoan(String loaiTaiKhoan);

    List<TaiKhoan> findByLoaiTaiKhoanIn(List<String> loaiTaiKhoan);
}
