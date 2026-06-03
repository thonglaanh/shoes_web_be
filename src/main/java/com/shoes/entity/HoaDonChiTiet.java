package com.shoes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hoa_don_chi_tiet")
    private Integer maHoaDonChiTiet;

    @ManyToOne
    @JoinColumn(name = "ma_hoa_don")
    private HoaDon hoaDon;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "gia_tien")
    private Double giaTien;

    @Column(name = "Size")
    private String size;

    @Column(name = "mau_sac")
    private String mauSac;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "ma_san_pham_chi_tiet")
    private Integer maSanPhamChiTiet;

    @Column(name = "ngay_khoi_tao")
    private LocalDateTime ngayKhoiTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    // Getters and Setters
    public Integer getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(Integer maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Integer getMaSanPhamChiTiet() {
        return maSanPhamChiTiet;
    }

    public void setMaSanPhamChiTiet(Integer maSanPhamChiTiet) {
        this.maSanPhamChiTiet = maSanPhamChiTiet;
    }

    public LocalDateTime getNgayKhoiTao() {
        return ngayKhoiTao;
    }

    public void setNgayKhoiTao(LocalDateTime ngayKhoiTao) {
        this.ngayKhoiTao = ngayKhoiTao;
    }

    public LocalDateTime getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(LocalDateTime ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
}
