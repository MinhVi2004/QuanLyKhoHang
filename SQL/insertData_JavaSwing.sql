USE `JavaSwing_Database`;

/* Dữ liệu mẫu cho bảng KhoHang */
INSERT INTO `KhoHang` (`TenKhoHang`) VALUES
('Kho hàng của THug24'),
('Kho hàng của AnDo'),
('Kho hàng của Thanh Điền'),
('Kho hàng của MinhVi');

/* Dữ liệu mẫu cho bảng TaiKhoan */
INSERT INTO `TaiKhoan` (`TenDangNhap`,		 	`MatKhau`, `TrangThai`, `Quyen`, `MaKhoHang`,   `HoTen`, 				`NgaySinh`,		 	`GioiTinh`, `SoDienThoai`, `Email`, 					`DiaChi`) 
VALUES 
						('THug24', 				'123456',	true,		'Admin',	1		, 'Ngô Tuấn Hưng', 			'1990-01-01', 		'Male', '	0123456789', 'thug24@gmail.com', 			'DKP1221'),
						('ctpnkAn1808', 		'123456', 	true, 		'User',		2		,'Diệp Thụy An', 			'1995-05-05', 		'Male', '	0123456789', 'an1808@example.com', 			'DCT1221'),
						('YangHoHocMon', 		'123456', 	true, 		'User',		3		,'Nguyễn Thanh Điền', 		'1998-08-08', 		'Male', '	0123456789', 'yangHoHocHon@example.com', 	'DKP1222'),
						('MinhViDepTrai04', 	'123456', 	true, 		'User',		4		,'Dương Văn Minh Vi', 		'1998-08-08', 		'Male', '	0123456789', 'minhViDepTrai04@example.com', 'DCT1222');


/* Dữ liệu mẫu cho bảng LoaiSanPham */
INSERT INTO `LoaiSanPham` (`TenLoaiSanPham`,				 `MaKhoHang`) VALUES
							('Các loại sản phẩm khác', 			1),


							('Lego', 1),
							('Figure', 1),
							('Xe mô tô', 1),

							('Laptop', 2),
							('Quần áo', 2);


/* Dữ liệu mẫu cho bảng SanPham */
INSERT INTO `SanPham` 	(`TenSanPham`, 																															`XuatXu`, 		`Gia`, 		`SoLuongConLai`, 	`TrangThai`,  `MaLoaiSanPham`, `MaKhoHang`, 	`AnhMinhHoa`) VALUES
						("Lego Ninjago 9447 Lasha's bite cycle", 																								'Đan Mạch', 	864000, 		10, 				true, 		 		2, 				1,		"JavaSwingProject/ah0yyd3vpybxaox0lak1"	),
						("Lego Ninjago 9446 Destiny's Bounty", 																									'Đan Mạch', 	7125000, 		2, 					true,		 		2, 				1,		"JavaSwingProject/jhzqkrkxoog3ldndv2zn"	),
						("Lego Ninjago 70668 Legacy Jay's Stome Fighter", 																						'Đan Mạch', 	1480000, 		20, 				true, 		 		2, 				1,		"JavaSwingProject/t3mbom8qupxduicsybwh"	),
                        
						("Hàng loại 1 - Mô Hình DragonBall Songoku vô cực 3 đầu thay thế cao 52cm nặng 4kg - Figure DragonBall - Có Hộp carton", 				'Nhật Bản', 	739000, 		10, 				true, 		 		3, 				1,		"JavaSwingProject/ceiimkgst4z0rvugf5mj"	),
						("Mô Hình Anime Bleach Ichigo Kurosaki cao Cao 18cm - ngang 10cm - nặng 150gram - Figure Anime Bleach - có hộp màu", 					'Nhật Bản', 	89000, 		    200, 				true,		 		3, 				1,		"JavaSwingProject/nrhhxtlimpax5muuyu7c"	),
						("Mô Hình Naruto dáng đứng siêu ngầu - Cao 30cm - ngang 12cm - nặng 1kg5 - Figure Naruto - Có hộp màu", 								'Nhật Bản', 	238000, 		20, 				true, 		 		3, 				1,		"JavaSwingProject/iesufe0ip1rrmc45m8jr"	);

/* Dữ liệu mẫu cho bảng NhaCungCap */
INSERT INTO `NhaCungCap` (`TenNCC`, 						`SoDienThoai`, `Email`,					 `MaKhoHang`) VALUES
							('Các nhà cung cấp khác', 		'0123456789', 'defaultNCC@gmail.com', 			1),
							('Công ty TNHH Thị Trường Sỉ', 	'0123456789', 'tts@gmail.com', 					1),
							('Ngôi Nhà Đồ Chơi', 			'0123456789', 'ngoNhaDoChoi@gmail.com', 		1),
                            ('Speedwagon', 					'0123456789', 'speedwagon@gmail.com', 			2),
							('Phong Vũ Computer', 			'0123456789', 'phongVu@gmail.com', 				2),
                            ('THug88Techno', 				'0123456789', 'thug88@gmail.com', 				1);



/* Dữ liệu mẫu cho bảng PhieuNhapKho */
INSERT INTO `PhieuNhapKho` (`NgayNhapKho`, 					`TongGiaTri`, 	`MaNCC`,  `MaKhoHang`, `TrangThai`) VALUES
							('2024-01-15 08:00:00', 		2840700000, 	2, 			 1,			"DaDuyet"),
							('2024-01-16 09:00:00', 		165600000, 		2, 			 1,			"DaDuyet"),
							('2024-01-17 10:00:00', 		7140000, 		3, 			 2,			"DaDuyet"),
							('2024-05-01 14:00:00', 		890000, 		2, 			 1,			"ChoDuyet"),
							('2024-04-25 7:00:00', 		89000, 		2, 			 1,			"Huy");

/* Dữ liệu mẫu cho bảng CTPNK */
INSERT INTO `CTPNK` (`DonGiaNhap`, `SoLuong`, 	`ThanhTien`, 	`MaPhieu`, 		`MaSanPham`) VALUES
					(864000, 		300, 		259200000, 			1, 				1),
					(7125000, 		300, 		2137500000, 		1, 				2),
					(1480000, 		300, 		444000000, 			1, 				3),
                    
                    (739000, 		200, 		147800000, 			2, 				4),
					(89000, 		200, 		17800000, 			2, 				5),
                    
					(238000, 		30, 		7140000, 			3, 				6),
					(89000, 		10, 		890000, 			4, 				5),
					(89000, 		1, 		89000, 			5, 				5);

/* Dữ liệu mẫu cho bảng PhieuXuatKho */
INSERT INTO `PhieuXuatKho` (`NgayXuatKho`, 				`TongGiaTri`, 	 	`MaKhoHang`, `TrangThai`) VALUES
							('2024-02-15 08:00:00', 		2787810000, 			1,			"DaDuyet"),
							('2024-02-16 09:00:00', 		750000, 		 		1,			"DaDuyet");

/* Dữ liệu mẫu cho bảng CTPXK */
INSERT INTO `CTPXK` (`DonGia`, `SoLuong`,	 `ThanhTien`,  		`MaPhieu`, `MaSanPham`) VALUES
					(864000, 		290, 		250560000, 				1, 		1),
					(7125000, 		298,		2123250000, 			1, 		2),
					(1480000, 		280, 		414000000, 				1, 		3),
                    
                    (739000, 		190, 		14041000, 				2, 		4),
					(238000, 		10	, 		2380000, 				2, 		6);

SELECT pxk.NgayXuatKho, sp.TenSanPham as TenSanPham, SUM(ct.SoLuong) as SoLuong, SUM(ct.ThanhTien) as TongTien FROM PhieuXuatKho pxk 
JOIN CTPXK ct ON pxk.MaPhieu = ct.MaPhieu
JOIN SanPham sp ON sp.MaSanPham = ct.MaSanPham
WHERE pxk.MaKhoHang = 1
AND  pxk.TrangThai = 'DaDuyet'
AND  pxk.NgayXuatKho BETWEEN COALESCE(NULL, '2010-01-01') AND COALESCE(NULL, CURRENT_DATE())
GROUP BY pxk.NgayXuatKho, TenSanPham
ORDER BY TongTien desc;

SELECT pnk.NgayNhapKho, sp.TenSanPham as TenSanPham, SUM(ct.SoLuong) as SoLuong, SUM(ct.ThanhTien) as TongTien FROM PhieuNhapKho pnk 
JOIN CTPNK ct ON pnk.MaPhieu = ct.MaPhieu
JOIN SanPham sp ON sp.MaSanPham = ct.MaSanPham
WHERE pnk.MaKhoHang = 1
AND  pnk.TrangThai = 'DaDuyet'
AND  pnk.NgayNhapKho BETWEEN COALESCE(NULL, '2010-01-01') AND COALESCE(NULL, CURRENT_DATE())
GROUP BY pnk.NgayNhapKho, TenSanPham
ORDER BY TongTien desc;

SELECT SUM(TongGiaTri) AS TongGiaTri FROM PhieuXuatKho 
            WHERE MaKhoHang = 1
            AND TrangThai = 'DaDuyet'
            AND NgayXuatKho BETWEEN COALESCE('2024-02-16', '2010-01-01') AND COALESCE(NULL, CURRENT_DATE());

SELECT SUM(TongGiaTri) AS TongGiaTri FROM PhieuNhapKho 
            WHERE MaKhoHang = 1
            AND TrangThai = 'DaDuyet'
            AND NgayNhapKho BETWEEN COALESCE('2024-01-16', '2010-01-01') AND COALESCE(NULL, CURRENT_DATE());

