package com.shoes.dto;

import java.time.LocalDateTime;

public class DanhGiaResponse {
    private Integer maDanhGia;
    private Integer maSanPham;
    private Integer maTaiKhoan;
    private String tenNguoiDung;
    private Integer soSao;
    private String comment;
    private LocalDateTime ngayTao;

    public DanhGiaResponse() {
    }

    public DanhGiaResponse(Integer maDanhGia, Integer maSanPham, Integer maTaiKhoan,
            String tenNguoiDung, Integer soSao, String comment, LocalDateTime ngayTao) {
        this.maDanhGia = maDanhGia;
        this.maSanPham = maSanPham;
        this.maTaiKhoan = maTaiKhoan;
        this.tenNguoiDung = tenNguoiDung;
        this.soSao = soSao;
        this.comment = comment;
        this.ngayTao = ngayTao;
    }

    // Getters and Setters
    public Integer getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(Integer maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public Integer getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

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

    public Integer getSoSao() {
        return soSao;
    }

    public void setSoSao(Integer soSao) {
        this.soSao = soSao;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}
