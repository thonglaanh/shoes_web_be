package com.shoes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "giam_gia")
public class GiamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_giam_gia")
    private Integer maGiamGia;

    @Column(name = "ten_giam_gia")
    private String tenGiamGia;

    @Column(name = "ma_code", unique = true)
    private String maCode;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "muc_giam_gia")
    private String mucGiamGia;

    @Column(name = "loai_giam_gia")
    private Integer loaiGiamGia;

    @Column(name = "giam_toi_da")
    private Integer giamToiDa;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "dieu_kien_ap_dung")
    private Integer dieuKienApDung;

    @Column(name = "ngay_bat_dau")
    private LocalDateTime ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDateTime ngayKetThuc;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_khoi_tao")
    private LocalDateTime ngayKhoiTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    // Getters and Setters
    public Integer getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(Integer maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getTenGiamGia() {
        return tenGiamGia;
    }

    public void setTenGiamGia(String tenGiamGia) {
        this.tenGiamGia = tenGiamGia;
    }

    public String getMaCode() {
        return maCode;
    }

    public void setMaCode(String maCode) {
        this.maCode = maCode;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(String mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public Integer getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(Integer loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public Integer getGiamToiDa() {
        return giamToiDa;
    }

    public void setGiamToiDa(Integer giamToiDa) {
        this.giamToiDa = giamToiDa;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDieuKienApDung() {
        return dieuKienApDung;
    }

    public void setDieuKienApDung(Integer dieuKienApDung) {
        this.dieuKienApDung = dieuKienApDung;
    }

    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
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
