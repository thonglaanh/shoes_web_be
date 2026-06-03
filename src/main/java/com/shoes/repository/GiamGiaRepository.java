package com.shoes.repository;

import com.shoes.entity.GiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GiamGiaRepository extends JpaRepository<GiamGia, Integer> {

    List<GiamGia> findByTrangThai(Integer trangThai);

    List<GiamGia> findByNgayBatDauLessThanEqualAndNgayKetThucGreaterThanEqual(
            LocalDateTime now1, LocalDateTime now2);

    Optional<GiamGia> findByMaCode(String maCode);
}
