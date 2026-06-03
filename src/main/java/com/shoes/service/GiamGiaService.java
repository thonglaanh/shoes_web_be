package com.shoes.service;

import com.shoes.entity.GiamGia;
import com.shoes.repository.GiamGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GiamGiaService {

    @Autowired
    private GiamGiaRepository repo;

    public List<GiamGia> getAll() {
        return repo.findAll();
    }

    public GiamGia getById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu giảm giá"));
    }

    public List<GiamGia> getByTrangThai(Integer trangThai) {
        return repo.findByTrangThai(trangThai);
    }

    public List<GiamGia> getActive() {
        LocalDateTime now = LocalDateTime.now();
        return repo.findByNgayBatDauLessThanEqualAndNgayKetThucGreaterThanEqual(now, now);
    }

    /**
     * Kiểm tra và tính toán mã giảm giá.
     * loaiGiamGia: 1 = % giảm, 2 = giảm cố định (VNĐ)
     * 
     * @param maCode     mã coupon
     * @param orderTotal tổng đơn hàng
     * @return map gồm: maGiamGia, tenGiamGia, loai, mucGiam, soTienGiam,
     *         soTienThanhToan
     */
    public Map<String, Object> applyCode(String maCode, double orderTotal) {
        GiamGia gg = repo.findByMaCode(maCode)
                .orElseThrow(() -> new RuntimeException("Mã giảm giá không tồn tại"));

        LocalDateTime now = LocalDateTime.now();

        if (gg.getTrangThai() == null || gg.getTrangThai() != 1) {
            throw new RuntimeException("Mã giảm giá không còn hiệu lực");
        }
        if (gg.getNgayBatDau() != null && now.isBefore(gg.getNgayBatDau())) {
            throw new RuntimeException("Mã giảm giá chưa đến ngày áp dụng");
        }
        if (gg.getNgayKetThuc() != null && now.isAfter(gg.getNgayKetThuc())) {
            throw new RuntimeException("Mã giảm giá đã hết hạn");
        }
        if (gg.getSoLuong() != null && gg.getSoLuong() <= 0) {
            throw new RuntimeException("Mã giảm giá đã hết lượt sử dụng");
        }
        // Kiểm tra điều kiện đơn hàng tối thiểu
        if (gg.getDieuKienApDung() != null && orderTotal < gg.getDieuKienApDung()) {
            throw new RuntimeException("Đơn hàng tối thiểu " +
                    String.format("%,.0f", (double) gg.getDieuKienApDung()) + "₫ để dùng mã này");
        }

        double mucGiam = 0;
        try {
            mucGiam = Double.parseDouble(gg.getMucGiamGia());
        } catch (Exception ignored) {
        }

        double soTienGiam;
        // loaiGiamGia: 1 = phần trăm, 2 = số tiền cố định
        if (gg.getLoaiGiamGia() != null && gg.getLoaiGiamGia() == 1) {
            soTienGiam = orderTotal * mucGiam / 100.0;
            if (gg.getGiamToiDa() != null && gg.getGiamToiDa() > 0) {
                soTienGiam = Math.min(soTienGiam, gg.getGiamToiDa());
            }
        } else {
            soTienGiam = mucGiam;
        }
        soTienGiam = Math.min(soTienGiam, orderTotal);

        Map<String, Object> result = new HashMap<>();
        result.put("maGiamGia", gg.getMaGiamGia());
        result.put("maCode", gg.getMaCode());
        result.put("tenGiamGia", gg.getTenGiamGia());
        result.put("loaiGiamGia", gg.getLoaiGiamGia());
        result.put("mucGiamGia", gg.getMucGiamGia());
        result.put("giamToiDa", gg.getGiamToiDa());
        result.put("soTienGiam", soTienGiam);
        result.put("soTienThanhToan", orderTotal - soTienGiam);
        return result;
    }

    /** Trừ soLuong khi đặt hàng thành công */
    public void consumeCode(String maCode) {
        repo.findByMaCode(maCode).ifPresent(gg -> {
            if (gg.getSoLuong() != null && gg.getSoLuong() > 0) {
                gg.setSoLuong(gg.getSoLuong() - 1);
                if (gg.getSoLuong() == 0)
                    gg.setTrangThai(0); // hết lượt → tắt
                gg.setNgayCapNhat(LocalDateTime.now());
                repo.save(gg);
            }
        });
    }

    public GiamGia create(GiamGia entity) {
        entity.setNgayKhoiTao(LocalDateTime.now());
        entity.setNgayCapNhat(LocalDateTime.now());
        return repo.save(entity);
    }

    public GiamGia update(Integer id, GiamGia entity) {
        GiamGia existing = getById(id);
        existing.setTenGiamGia(entity.getTenGiamGia());
        existing.setMaCode(entity.getMaCode());
        existing.setMoTa(entity.getMoTa());
        existing.setMucGiamGia(entity.getMucGiamGia());
        existing.setLoaiGiamGia(entity.getLoaiGiamGia());
        existing.setGiamToiDa(entity.getGiamToiDa());
        existing.setSoLuong(entity.getSoLuong());
        existing.setDieuKienApDung(entity.getDieuKienApDung());
        existing.setNgayBatDau(entity.getNgayBatDau());
        existing.setNgayKetThuc(entity.getNgayKetThuc());
        existing.setTrangThai(entity.getTrangThai());
        existing.setNgayCapNhat(LocalDateTime.now());
        return repo.save(existing);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
