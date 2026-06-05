-- Create danh_gia table for product reviews
IF NOT EXISTS (SELECT *
FROM sys.tables
WHERE name = 'danh_gia')
BEGIN
    CREATE TABLE danh_gia
    (
        ma_danh_gia INT PRIMARY KEY IDENTITY(1,1),
        ma_san_pham INT NOT NULL,
        ma_tai_khoan INT NOT NULL,
        so_sao INT NOT NULL DEFAULT 5,
        comment NVARCHAR(MAX),
        ngay_tao DATETIME DEFAULT GETDATE(),
        ngay_cap_nhat DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (ma_san_pham) REFERENCES san_pham(ma_san_pham) ON DELETE CASCADE,
        FOREIGN KEY (ma_tai_khoan) REFERENCES tai_khoan(ma_tai_khoan) ON DELETE CASCADE
    );

    CREATE INDEX idx_danh_gia_product ON danh_gia(ma_san_pham);
    CREATE INDEX idx_danh_gia_user ON danh_gia(ma_tai_khoan);
    CREATE INDEX idx_danh_gia_date ON danh_gia(ngay_tao);
END
GO
