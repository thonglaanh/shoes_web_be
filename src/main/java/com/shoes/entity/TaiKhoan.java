package com.shoes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tai_khoan")
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_tai_khoan")
    private Integer maTaiKhoan;

    @Column(name = "ten_nguoi_dung")
    private String tenNguoiDung;

    @Column(name = "loai_tai_khoan")
    private String loaiTaiKhoan;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "ngay_khoi_tao")
    private LocalDateTime ngayKhoiTao;

    @Column(name = "nguoi_khoi_tao")
    private String nguoiKhoiTao;

    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "nam_sinh")
    private LocalDateTime namSinh;

    // Getters and Setters
    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getNgayKhoiTao() {
        return ngayKhoiTao;
    }

    public void setNgayKhoiTao(LocalDateTime ngayKhoiTao) {
        this.ngayKhoiTao = ngayKhoiTao;
    }

    public String getNguoiKhoiTao() {
        return nguoiKhoiTao;
    }

    public void setNguoiKhoiTao(String nguoiKhoiTao) {
        this.nguoiKhoiTao = nguoiKhoiTao;
    }

    public LocalDateTime getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(LocalDateTime ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(LocalDateTime namSinh) {
        this.namSinh = namSinh;
    }
}
