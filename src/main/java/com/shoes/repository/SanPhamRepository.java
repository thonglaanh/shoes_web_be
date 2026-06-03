package com.shoes.repository;

import com.shoes.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

    List<SanPham> findTop5ByTenSanPhamContainingIgnoreCaseOrMoTaContainingIgnoreCase(String name, String desc);
}
