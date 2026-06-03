USE shoesdb;
GO

-- 1. THUONG HIEU
SET IDENTITY_INSERT thuong_hieu ON;
INSERT INTO thuong_hieu (ma_thuong_hieu, ten_thuong_hieu, trang_thai, nguoi_khoi_tao, nguoi_cap_nhat, ngay_khoi_tao, ngay_cap_nhat) VALUES
(1, N'Ananas', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(2, N'Bitis Hunter', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(3, N'Juno', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(4, N'Vascara', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(5, N'Giay Viet', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(6, N'Thuong Dinh', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE());
SET IDENTITY_INSERT thuong_hieu OFF;
GO

-- 2. CHAT LIEU
SET IDENTITY_INSERT chat_lieu ON;
INSERT INTO chat_lieu (ma_chat_lieu, ten_chat_lieu, trang_thai, nguoi_khoi_tao, nguoi_cap_nhat, ngay_khoi_tao, ngay_cap_nhat) VALUES
(1, N'Canvas', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(2, N'Da tong hop', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(3, N'Vai luoi Mesh', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(4, N'Da that', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(5, N'Cao su', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(6, N'Vai det Knit', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE());
SET IDENTITY_INSERT chat_lieu OFF;
GO

-- 3. XUAT XU
SET IDENTITY_INSERT xuat_xu ON;
INSERT INTO xuat_xu (ma_xuat_xu, ten_xuat_xu, trang_thai, nguoi_khoi_tao, nguoi_cap_nhat, ngay_khoi_tao, ngay_cap_nhat) VALUES
(1, N'Viet Nam', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(2, N'Trung Quoc', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(3, N'Campuchia', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE());
SET IDENTITY_INSERT xuat_xu OFF;
GO

-- 4. MAU SAC
SET IDENTITY_INSERT mau_sac ON;
INSERT INTO mau_sac (ma_mau_sac, ten_mau, trang_thai, nguoi_khoi_tao, nguoi_cap_nhat, ngay_khoi_tao, ngay_cap_nhat) VALUES
(1, N'Den', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(2, N'Trang', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(3, N'Do', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(4, N'Xanh Navy', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(5, N'Xam', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(6, N'Nau', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(7, N'Xanh Reu', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(8, N'Hong', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE());
SET IDENTITY_INSERT mau_sac OFF;
GO

-- 5. SIZE
SET IDENTITY_INSERT size ON;
INSERT INTO size (ma_size, ten_size, trang_thai, nguoi_khoi_tao, nguoi_cap_nhat, ngay_khoi_tao, ngay_cap_nhat) VALUES
(1, N'36', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(2, N'37', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(3, N'38', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(4, N'39', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(5, N'40', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(6, N'41', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(7, N'42', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(8, N'43', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE()),
(9, N'44', 1, N'ADMIN', N'ADMIN', GETDATE(), GETDATE());
SET IDENTITY_INSERT size OFF;
GO

-- 6. SAN PHAM
SET IDENTITY_INSERT san_pham ON;
INSERT INTO san_pham (ma_san_pham, ten_san_pham, mo_ta, trang_thai, nguoi_khoi_tao, nguoi_cap_nhat, ma_chat_lieu, ma_thuong_hieu, ma_xuat_xu, ngay_khoi_tao, ngay_cap_nhat) VALUES
(1, N'Ananas Vintas Mule', N'Giay mule phong cach retro, canvas cao cap, de cao su ben bi.', 1, N'ADMIN', N'ADMIN', 1, 1, 1, GETDATE(), GETDATE()),
(2, N'Ananas Basas Evergreen', N'Giay sneaker co dien, canvas 12oz ben chac, de vulcanized chong truot.', 1, N'ADMIN', N'ADMIN', 1, 1, 1, GETDATE(), GETDATE()),
(3, N'Bitis Hunter X Festive', N'Giay the thao cong nghe LiteFlex sieu nhe, vai knit thoang khi.', 1, N'ADMIN', N'ADMIN', 6, 2, 1, GETDATE(), GETDATE()),
(4, N'Bitis Hunter Street', N'Giay casual nang dong, thiet ke streetwear hien dai.', 1, N'ADMIN', N'ADMIN', 3, 2, 1, GETDATE(), GETDATE()),
(5, N'Juno Giay Cao Got Mui Nhon', N'Giay cao got 7cm thanh lich, da tong hop mem mai.', 1, N'ADMIN', N'ADMIN', 2, 3, 1, GETDATE(), GETDATE()),
(6, N'Vascara Sandal Quai Ngang', N'Sandal thoi trang nu, quai da mem, de chunky 5cm.', 1, N'ADMIN', N'ADMIN', 4, 4, 1, GETDATE(), GETDATE()),
(7, N'Thuong Dinh Classic', N'Giay bata huyen thoai Viet Nam, vai ben, de cao su tu nhien.', 1, N'ADMIN', N'ADMIN', 1, 6, 1, GETDATE(), GETDATE()),
(8, N'Bitis Hunter X Layered', N'Giay chunky sneaker de layered day dan, LiteKnit 2.0 thoang khi.', 1, N'ADMIN', N'ADMIN', 6, 2, 1, GETDATE(), GETDATE());
SET IDENTITY_INSERT san_pham OFF;
GO

-- 7. SAN PHAM CHI TIET
SET IDENTITY_INSERT san_pham_chi_tiet ON;
INSERT INTO san_pham_chi_tiet (ma_san_pham_chi_tiet, so_luong, gia_tien, trang_thai, hinh_anh, ma_san_pham, ma_mau_sac, ma_size, ngay_khoi_tao, ngay_cap_nhat) VALUES
(1,  50, 599000, 1, NULL, 1, 1, 4, GETDATE(), GETDATE()),
(2,  45, 599000, 1, NULL, 1, 1, 5, GETDATE(), GETDATE()),
(3,  30, 599000, 1, NULL, 1, 1, 6, GETDATE(), GETDATE()),
(4,  40, 599000, 1, NULL, 1, 2, 4, GETDATE(), GETDATE()),
(5,  35, 599000, 1, NULL, 1, 2, 5, GETDATE(), GETDATE()),
(6,  25, 599000, 1, NULL, 1, 2, 6, GETDATE(), GETDATE()),
(7,  60, 499000, 1, NULL, 2, 1, 3, GETDATE(), GETDATE()),
(8,  55, 499000, 1, NULL, 2, 1, 4, GETDATE(), GETDATE()),
(9,  50, 499000, 1, NULL, 2, 1, 5, GETDATE(), GETDATE()),
(10, 45, 499000, 1, NULL, 2, 1, 6, GETDATE(), GETDATE()),
(11, 40, 499000, 1, NULL, 2, 1, 7, GETDATE(), GETDATE()),
(12, 35, 529000, 1, NULL, 2, 4, 4, GETDATE(), GETDATE()),
(13, 30, 529000, 1, NULL, 2, 4, 5, GETDATE(), GETDATE()),
(14, 25, 529000, 1, NULL, 2, 4, 6, GETDATE(), GETDATE()),
(15, 70, 799000, 1, NULL, 3, 2, 3, GETDATE(), GETDATE()),
(16, 65, 799000, 1, NULL, 3, 2, 4, GETDATE(), GETDATE()),
(17, 60, 799000, 1, NULL, 3, 2, 5, GETDATE(), GETDATE()),
(18, 55, 799000, 1, NULL, 3, 2, 6, GETDATE(), GETDATE()),
(19, 50, 799000, 1, NULL, 3, 2, 7, GETDATE(), GETDATE()),
(20, 40, 799000, 1, NULL, 3, 1, 4, GETDATE(), GETDATE()),
(21, 35, 799000, 1, NULL, 3, 1, 5, GETDATE(), GETDATE()),
(22, 30, 799000, 1, NULL, 3, 1, 6, GETDATE(), GETDATE()),
(23, 25, 799000, 1, NULL, 3, 1, 7, GETDATE(), GETDATE()),
(24, 45, 699000, 1, NULL, 4, 3, 3, GETDATE(), GETDATE()),
(25, 40, 699000, 1, NULL, 4, 3, 4, GETDATE(), GETDATE()),
(26, 35, 699000, 1, NULL, 4, 3, 5, GETDATE(), GETDATE()),
(27, 30, 699000, 1, NULL, 4, 3, 6, GETDATE(), GETDATE()),
(28, 50, 699000, 1, NULL, 4, 5, 4, GETDATE(), GETDATE()),
(29, 45, 699000, 1, NULL, 4, 5, 5, GETDATE(), GETDATE()),
(30, 40, 699000, 1, NULL, 4, 5, 6, GETDATE(), GETDATE()),
(31, 35, 699000, 1, NULL, 4, 5, 7, GETDATE(), GETDATE()),
(32, 30, 459000, 1, NULL, 5, 1, 1, GETDATE(), GETDATE()),
(33, 35, 459000, 1, NULL, 5, 1, 2, GETDATE(), GETDATE()),
(34, 40, 459000, 1, NULL, 5, 1, 3, GETDATE(), GETDATE()),
(35, 25, 459000, 1, NULL, 5, 1, 4, GETDATE(), GETDATE()),
(36, 20, 489000, 1, NULL, 5, 8, 1, GETDATE(), GETDATE()),
(37, 25, 489000, 1, NULL, 5, 8, 2, GETDATE(), GETDATE()),
(38, 30, 489000, 1, NULL, 5, 8, 3, GETDATE(), GETDATE()),
(39, 35, 389000, 1, NULL, 6, 6, 1, GETDATE(), GETDATE()),
(40, 30, 389000, 1, NULL, 6, 6, 2, GETDATE(), GETDATE()),
(41, 25, 389000, 1, NULL, 6, 6, 3, GETDATE(), GETDATE()),
(42, 20, 389000, 1, NULL, 6, 6, 4, GETDATE(), GETDATE()),
(43, 30, 389000, 1, NULL, 6, 1, 1, GETDATE(), GETDATE()),
(44, 25, 389000, 1, NULL, 6, 1, 2, GETDATE(), GETDATE()),
(45, 20, 389000, 1, NULL, 6, 1, 3, GETDATE(), GETDATE()),
(46, 100, 199000, 1, NULL, 7, 2, 3, GETDATE(), GETDATE()),
(47, 95,  199000, 1, NULL, 7, 2, 4, GETDATE(), GETDATE()),
(48, 90,  199000, 1, NULL, 7, 2, 5, GETDATE(), GETDATE()),
(49, 85,  199000, 1, NULL, 7, 2, 6, GETDATE(), GETDATE()),
(50, 80,  199000, 1, NULL, 7, 2, 7, GETDATE(), GETDATE()),
(51, 75,  199000, 1, NULL, 7, 2, 8, GETDATE(), GETDATE()),
(52, 60, 219000, 1, NULL, 7, 7, 4, GETDATE(), GETDATE()),
(53, 55, 219000, 1, NULL, 7, 7, 5, GETDATE(), GETDATE()),
(54, 50, 219000, 1, NULL, 7, 7, 6, GETDATE(), GETDATE()),
(55, 45, 219000, 1, NULL, 7, 7, 7, GETDATE(), GETDATE()),
(56, 55, 899000, 1, NULL, 8, 2, 3, GETDATE(), GETDATE()),
(57, 50, 899000, 1, NULL, 8, 2, 4, GETDATE(), GETDATE()),
(58, 45, 899000, 1, NULL, 8, 2, 5, GETDATE(), GETDATE()),
(59, 40, 899000, 1, NULL, 8, 2, 6, GETDATE(), GETDATE()),
(60, 35, 899000, 1, NULL, 8, 2, 7, GETDATE(), GETDATE()),
(61, 50, 899000, 1, NULL, 8, 1, 4, GETDATE(), GETDATE()),
(62, 45, 899000, 1, NULL, 8, 1, 5, GETDATE(), GETDATE()),
(63, 40, 899000, 1, NULL, 8, 1, 6, GETDATE(), GETDATE()),
(64, 35, 899000, 1, NULL, 8, 1, 7, GETDATE(), GETDATE()),
(65, 30, 899000, 1, NULL, 8, 1, 8, GETDATE(), GETDATE());
SET IDENTITY_INSERT san_pham_chi_tiet OFF;
GO

-- 8. TAI KHOAN
SET IDENTITY_INSERT tai_khoan ON;
INSERT INTO tai_khoan (ma_tai_khoan, ten_dang_nhap, ten_nguoi_dung, loai_tai_khoan, mat_khau, trang_thai, ngay_khoi_tao, nguoi_khoi_tao, ngay_cap_nhat, gioi_tinh, so_dien_thoai, email) VALUES
(1, N'admin@shoesshop.vn', N'Quan Tri Vien', N'ADMIN', N'admin123', N'ACTIVE', GETDATE(), N'SYSTEM', GETDATE(), N'Nam', N'0901234567', N'admin@shoesshop.vn'),
(2, N'nhanvien@shoesshop.vn', N'Nhan Vien 01', N'NHANVIEN', N'nv123456', N'ACTIVE', GETDATE(), N'ADMIN', GETDATE(), N'Nu', N'0912345678', N'nhanvien@shoesshop.vn'),
(3, N'user@gmail.com', N'Nguyen Van Test', N'USER', N'user1234', N'ACTIVE', GETDATE(), N'SYSTEM', GETDATE(), N'Nam', N'0987654321', N'user@gmail.com');
SET IDENTITY_INSERT tai_khoan OFF;
GO

PRINT N'Done!';
GO
