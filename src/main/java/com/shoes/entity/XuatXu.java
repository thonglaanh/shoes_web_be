package com.shoes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "xuat_xu")
public class XuatXu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_xuat_xu")
    private Integer maXuatXu;

    @Column(name = "ten_xuat_xu")
    private String tenXuatXu;

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

    public Integer getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(Integer maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
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
