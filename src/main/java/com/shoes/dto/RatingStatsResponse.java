package com.shoes.dto;

public class RatingStatsResponse {
    private Integer maSanPham;
    private Double averageRating;
    private Integer totalReviews;

    public RatingStatsResponse() {
    }

    public RatingStatsResponse(Integer maSanPham, Double averageRating, Integer totalReviews) {
        this.maSanPham = maSanPham;
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

    // Getters and Setters
    public Integer getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }
}
