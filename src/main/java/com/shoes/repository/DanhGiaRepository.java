package com.shoes.repository;

import com.shoes.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {
    List<DanhGia> findBySanPham_MaSanPham(Integer maSanPham);

    List<DanhGia> findByTaiKhoan_MaTaiKhoan(Integer maTaiKhoan);
}
