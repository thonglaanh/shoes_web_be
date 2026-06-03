package com.shoes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mau_sac")
public class MauSac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_mau_sac")
    private Integer maMauSac;

    @Column(name = "ten_mau")
    private String tenMau;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "nguoi_khoi_tao")
    private String nguoiKhoiTao;

    @Column(name = "nguoi_cap_nhat")
    private String nguoiCapNhat;

    @Column(name = "ngay_khoi_tao")
    private LocalDateTime ngayKhoiTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    public Integer getMaMauSac() {
        return maMauSac;
    }

    public void setMaMauSac(Integer maMauSac) {
        this.maMauSac = maMauSac;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getNguoiKhoiTao() {
        return nguoiKhoiTao;
    }

    public void setNguoiKhoiTao(String nguoiKhoiTao) {
        this.nguoiKhoiTao = nguoiKhoiTao;
    }

    public String getNguoiCapNhat() {
        return nguoiCapNhat;
    }

    public void setNguoiCapNhat(String nguoiCapNhat) {
        this.nguoiCapNhat = nguoiCapNhat;
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
