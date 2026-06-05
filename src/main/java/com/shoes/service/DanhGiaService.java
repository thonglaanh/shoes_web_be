package com.shoes.service;

import com.shoes.dto.DanhGiaResponse;
import com.shoes.entity.DanhGia;
import com.shoes.entity.SanPham;
import com.shoes.entity.TaiKhoan;
import com.shoes.repository.DanhGiaRepository;
import com.shoes.repository.SanPhamRepository;
import com.shoes.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DanhGiaService {

    @Autowired
    private DanhGiaRepository danhGiaRepo;

    @Autowired
    private SanPhamRepository sanPhamRepo;

    @Autowired
    private TaiKhoanRepository taiKhoanRepo;

    public List<DanhGiaResponse> getByProductId(Integer maSanPham) {
        List<DanhGia> danhGias = danhGiaRepo.findBySanPham_MaSanPham(maSanPham);
        return danhGias.stream().map(dg -> new DanhGiaResponse(
                dg.getMaDanhGia(),
                dg.getSanPham().getMaSanPham(),
                dg.getTaiKhoan().getMaTaiKhoan(),
                dg.getTaiKhoan().getTenNguoiDung(),
                dg.getSoSao(),
                dg.getComment(),
                dg.getNgayTao())).collect(Collectors.toList());
    }

    public List<DanhGiaResponse> getByUserId(Integer maTaiKhoan) {
        List<DanhGia> danhGias = danhGiaRepo.findByTaiKhoan_MaTaiKhoan(maTaiKhoan);
        return danhGias.stream().map(dg -> new DanhGiaResponse(
                dg.getMaDanhGia(),
                dg.getSanPham().getMaSanPham(),
                dg.getTaiKhoan().getMaTaiKhoan(),
                dg.getTaiKhoan().getTenNguoiDung(),
                dg.getSoSao(),
                dg.getComment(),
                dg.getNgayTao())).collect(Collectors.toList());
    }

    public DanhGiaResponse create(Integer maSanPham, Integer maTaiKhoan, Integer soSao, String comment) {
        SanPham sanPham = sanPhamRepo.findById(maSanPham)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        TaiKhoan taiKhoan = taiKhoanRepo.findById(maTaiKhoan)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));

        DanhGia danhGia = new DanhGia();
        danhGia.setSanPham(sanPham);
        danhGia.setTaiKhoan(taiKhoan);
        danhGia.setSoSao(soSao);
        danhGia.setComment(comment);
        danhGia.setNgayTao(LocalDateTime.now());
        danhGia.setNgayCapNhat(LocalDateTime.now());

        DanhGia saved = danhGiaRepo.save(danhGia);
        return new DanhGiaResponse(
                saved.getMaDanhGia(),
                saved.getSanPham().getMaSanPham(),
                saved.getTaiKhoan().getMaTaiKhoan(),
                saved.getTaiKhoan().getTenNguoiDung(),
                saved.getSoSao(),
                saved.getComment(),
                saved.getNgayTao());
    }

    public DanhGiaResponse update(Integer maDanhGia, Integer soSao, String comment) {
        DanhGia danhGia = danhGiaRepo.findById(maDanhGia)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đánh giá"));

        danhGia.setSoSao(soSao);
        danhGia.setComment(comment);
        danhGia.setNgayCapNhat(LocalDateTime.now());

        DanhGia updated = danhGiaRepo.save(danhGia);
        return new DanhGiaResponse(
                updated.getMaDanhGia(),
                updated.getSanPham().getMaSanPham(),
                updated.getTaiKhoan().getMaTaiKhoan(),
                updated.getTaiKhoan().getTenNguoiDung(),
                updated.getSoSao(),
                updated.getComment(),
                updated.getNgayTao());
    }

    public void delete(Integer maDanhGia) {
        danhGiaRepo.deleteById(maDanhGia);
    }

    public com.shoes.dto.RatingStatsResponse getProductRatingStats(Integer maSanPham) {
        List<DanhGia> reviews = danhGiaRepo.findBySanPham_MaSanPham(maSanPham);

        if (reviews.isEmpty()) {
            return new com.shoes.dto.RatingStatsResponse(maSanPham, 0.0, 0);
        }

        double average = reviews.stream()
                .mapToInt(DanhGia::getSoSao)
                .average()
                .orElse(0.0);

        return new com.shoes.dto.RatingStatsResponse(
                maSanPham,
                Math.round(average * 10.0) / 10.0, // Round to 1 decimal place
                reviews.size());
    }
}
