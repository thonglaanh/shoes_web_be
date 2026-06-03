package com.shoes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_size")
    private Integer maSize;

    @Column(name = "ten_size")
    private String tenSize;

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

    public Integer getMaSize() {
        return maSize;
    }

    public void setMaSize(Integer maSize) {
        this.maSize = maSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
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
