package com.shoes.repository;

import com.shoes.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
    List<DonHang> findByMaKhachHang(Integer maKhachHang);

    List<DonHang> findByTrangThai(String trangThai);
}
