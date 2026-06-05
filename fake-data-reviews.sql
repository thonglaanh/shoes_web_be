-- Insert sample reviews data for testing
USE shoesdb;
GO

-- Insert test reviews (make sure tai_khoan and san_pham exist first)
SET IDENTITY_INSERT danh_gia ON;
INSERT INTO danh_gia
    (ma_danh_gia, ma_san_pham, ma_tai_khoan, so_sao, comment, ngay_tao, ngay_cap_nhat)
VALUES
    (1, 1, 1, 5, N'Sản phẩm rất tốt, chất liệu đẹp, giao hàng nhanh. Sẽ quay lại mua tiếp.', DATEADD(DAY, -7, GETDATE()), DATEADD(DAY, -7, GETDATE())),
    (2, 1, 2, 4, N'Hơi hẹp một tí nhưng chất lượng ổn. Bảng size có vẻ không chính xác lắm.', DATEADD(DAY, -5, GETDATE()), DATEADD(DAY, -5, GETDATE())),
    (3, 1, 3, 5, N'Rất hài lòng với sản phẩm, đúng như hình ảnh. Giao hàng an toàn.', DATEADD(DAY, -3, GETDATE()), DATEADD(DAY, -3, GETDATE())),
    (4, 2, 1, 4, N'Giày êm chân, đi cả ngày không mệt. Giá hơi cao nhưng chất lượng xứng đáng.', DATEADD(DAY, -2, GETDATE()), DATEADD(DAY, -2, GETDATE()));
SET IDENTITY_INSERT danh_gia OFF;
GO
