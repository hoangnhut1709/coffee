USE [master]
GO
/****** Object:  Database [Coffee]    Script Date: 12/10/2020 15:52:09 ******/
CREATE DATABASE [Coffee] ON  PRIMARY 
( NAME = N'Coffee', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\Coffee.mdf' , SIZE = 2304KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Coffee_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\Coffee_log.LDF' , SIZE = 768KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Coffee] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Coffee].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Coffee] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [Coffee] SET ANSI_NULLS OFF
GO
ALTER DATABASE [Coffee] SET ANSI_PADDING OFF
GO
ALTER DATABASE [Coffee] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [Coffee] SET ARITHABORT OFF
GO
ALTER DATABASE [Coffee] SET AUTO_CLOSE ON
GO
ALTER DATABASE [Coffee] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [Coffee] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [Coffee] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [Coffee] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [Coffee] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [Coffee] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [Coffee] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [Coffee] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [Coffee] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [Coffee] SET  ENABLE_BROKER
GO
ALTER DATABASE [Coffee] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [Coffee] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [Coffee] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [Coffee] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [Coffee] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [Coffee] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [Coffee] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [Coffee] SET  READ_WRITE
GO
ALTER DATABASE [Coffee] SET RECOVERY SIMPLE
GO
ALTER DATABASE [Coffee] SET  MULTI_USER
GO
ALTER DATABASE [Coffee] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [Coffee] SET DB_CHAINING OFF
GO
USE [Coffee]
GO
/****** Object:  Table [dbo].[PHANLOAI_TAIKHOAN]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHANLOAI_TAIKHOAN](
	[maLTK] [int] NOT NULL,
	[mota] [nvarchar](50) NULL,
 CONSTRAINT [PK_PHANLOAI_TAIKHOAN] PRIMARY KEY CLUSTERED 
(
	[maLTK] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[PHANLOAI_TAIKHOAN] ([maLTK], [mota]) VALUES (1, N'Admin')
INSERT [dbo].[PHANLOAI_TAIKHOAN] ([maLTK], [mota]) VALUES (2, N'Nhanvien')
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[maNV] [nvarchar](30) NOT NULL,
	[tenNV] [nvarchar](50) NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
	[sdt] [nvarchar](10) NULL,
	[email] [nvarchar](30) NULL,
	[diaChi] [nvarchar](50) NULL,
	[hinh] [nvarchar](50) NULL,
 CONSTRAINT [PK_NHANVIEN] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[NHANVIEN] ([maNV], [tenNV], [gioiTinh], [ngaySinh], [sdt], [email], [diaChi], [hinh]) VALUES (N'NV00001', N'Vu Bang', 1, CAST(0x5F250B00 AS Date), N'0215845636', N'bang@gmail.com', N'Can Tho', N'1.jpg')
INSERT [dbo].[NHANVIEN] ([maNV], [tenNV], [gioiTinh], [ngaySinh], [sdt], [email], [diaChi], [hinh]) VALUES (N'NV00002', N'Nhat Di', 1, CAST(0xC3240B00 AS Date), N'0254585693', N'di@gmail.com', N'Can Tho', N'2.png')
INSERT [dbo].[NHANVIEN] ([maNV], [tenNV], [gioiTinh], [ngaySinh], [sdt], [email], [diaChi], [hinh]) VALUES (N'NV00003', N'Chi Thuan', 1, CAST(0x22250B00 AS Date), N'0212523656', N'thuan@gmail.com', N'Can Tho', N'3.png')
/****** Object:  Table [dbo].[NHACUNGCAP]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHACUNGCAP](
	[maNCC] [nvarchar](30) NOT NULL,
	[tenNCC] [nvarchar](500) NULL,
	[loaiSP] [nvarchar](50) NULL,
	[giaMua] [int] NULL,
	[diaChi] [nvarchar](500) NULL,
	[sdt] [nvarchar](10) NULL,
 CONSTRAINT [PK_NHACUNGCAP] PRIMARY KEY CLUSTERED 
(
	[maNCC] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[NHACUNGCAP] ([maNCC], [tenNCC], [loaiSP], [giaMua], [diaChi], [sdt]) VALUES (N'NCC001', N'Vườn trái cây cửu long', N'Trái cây', 15000000, N'Cần thơ', N'0254856236')
INSERT [dbo].[NHACUNGCAP] ([maNCC], [tenNCC], [loaiSP], [giaMua], [diaChi], [sdt]) VALUES (N'NCC002', N'Cty TNHH nước giải khác', N'Nước đóng chai', 25000000, N'Cần thơ', N'0215485362')
INSERT [dbo].[NHACUNGCAP] ([maNCC], [tenNCC], [loaiSP], [giaMua], [diaChi], [sdt]) VALUES (N'NCC003', N'Cty TNHH cà phê Bưu mê thuộc', N'Coffee', 300000000, N'Cần thơ', N'0215485265')
/****** Object:  Table [dbo].[QLSANPHAM]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QLSANPHAM](
	[maQLSP] [int] IDENTITY(1,1) NOT NULL,
	[maNCC] [nvarchar](30) NULL,
	[tenLoai] [nvarchar](50) NULL,
	[soLuong] [int] NULL,
	[tenSP] [nvarchar](30) NULL,
	[ngay] [date] NULL,
 CONSTRAINT [PK_QLSANPHAM] PRIMARY KEY CLUSTERED 
(
	[maQLSP] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[QLSANPHAM] ON
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (1, N'NCC002', N'Nước đóng chai', 10, N'Sting', CAST(0x4C410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (2, N'NCC002', N'Nước đóng chai', 20, N'7 Up', CAST(0x6B410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (3, N'NCC002', N'Nước đóng chai', 30, N'0 độ', CAST(0x8A410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (4, N'NCC002', N'Nước đóng chai', 40, N'Revive', CAST(0xA8410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (5, N'NCC002', N'Nước đóng chai', 50, N'Nước suối', CAST(0xC7410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (6, N'NCC001', N'Trái cây', 10, N'Cam', CAST(0xD9420B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (7, N'NCC001', N'Trái cây', 20, N'Dừa tươi', CAST(0x8B410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (8, N'NCC001', N'Trái cây', 30, N'Bơ', CAST(0x6D410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (9, N'NCC001', N'Trái cây', 40, N'Sa bô', CAST(0x8D410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (10, N'NCC001', N'Trái cây', 50, N'Mản cầu', CAST(0xCB410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (11, N'NCC003', N'Coffee', 5, N'Capuchino', CAST(0xAB410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (12, N'NCC003', N'Coffee', 5, N'Cà phê chồn', CAST(0xCB410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (13, N'NCC003', N'Coffee', 5, N'Moka', CAST(0xEA410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (14, N'NCC003', N'Coffee', 5, N'Chery', CAST(0xC8410B00 AS Date))
INSERT [dbo].[QLSANPHAM] ([maQLSP], [maNCC], [tenLoai], [soLuong], [tenSP], [ngay]) VALUES (15, N'NCC003', N'Coffee', 5, N'Culi', CAST(0x6C410B00 AS Date))
SET IDENTITY_INSERT [dbo].[QLSANPHAM] OFF
/****** Object:  Table [dbo].[HOADON]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[maHD] [nvarchar](30) NOT NULL,
	[ngayLap] [date] NULL,
	[maNV] [nvarchar](30) NULL,
 CONSTRAINT [PK_HOADON] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[HOADON] ([maHD], [ngayLap], [maNV]) VALUES (N'HD0001', CAST(0xAD410B00 AS Date), N'NV00002')
INSERT [dbo].[HOADON] ([maHD], [ngayLap], [maNV]) VALUES (N'HD0002', CAST(0x57430B00 AS Date), N'NV00002')
INSERT [dbo].[HOADON] ([maHD], [ngayLap], [maNV]) VALUES (N'HD0003', CAST(0x7C400B00 AS Date), N'NV00003')
INSERT [dbo].[HOADON] ([maHD], [ngayLap], [maNV]) VALUES (N'HD0004', CAST(0x0F3F0B00 AS Date), N'NV00001')
INSERT [dbo].[HOADON] ([maHD], [ngayLap], [maNV]) VALUES (N'HD0005', CAST(0xA23D0B00 AS Date), N'NV00002')
/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TAIKHOAN](
	[maTK] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[maLTK] [int] NULL,
	[maNV] [nvarchar](30) NULL,
 CONSTRAINT [PK_TAIKHOAN] PRIMARY KEY CLUSTERED 
(
	[maTK] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[TAIKHOAN] ON
INSERT [dbo].[TAIKHOAN] ([maTK], [username], [password], [maLTK], [maNV]) VALUES (1, N'THUAN', N'1234567', 2, N'NV00003')
INSERT [dbo].[TAIKHOAN] ([maTK], [username], [password], [maLTK], [maNV]) VALUES (2, N'DI', N'1234567', 2, N'NV00002')
INSERT [dbo].[TAIKHOAN] ([maTK], [username], [password], [maLTK], [maNV]) VALUES (20, N'VUBANG', N'1234567', 1, N'NV00001')
SET IDENTITY_INSERT [dbo].[TAIKHOAN] OFF
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[maSP] [nvarchar](30) NOT NULL,
	[tenSP] [nvarchar](50) NULL,
	[maQLSP] [int] NULL,
	[giaBan] [int] NULL,
 CONSTRAINT [PK_SANPHAM] PRIMARY KEY CLUSTERED 
(
	[maSP] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP001', N'Sting', 1, 20000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP002', N'7 up', 2, 20000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP003', N'0 độ', 3, 20000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP004', N'Revive', 4, 20000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP005', N'Nước suối', 5, 20000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP006', N'Cam', 6, 25000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP007', N'Dừa tươi', 7, 20000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP008', N'Bơ', 8, 20000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP009', N'Sa bô', 9, 25000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP010', N'Mản cầu', 10, 25000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP011', N'Capuchino', 11, 40000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP012', N'Cà phê chồn', 12, 40000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP013', N'Moka', 13, 40000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP014', N'Chery', 14, 40000)
INSERT [dbo].[SANPHAM] ([maSP], [tenSP], [maQLSP], [giaBan]) VALUES (N'SP015', N'Culi', 15, 50000)
/****** Object:  Table [dbo].[HOADON_CHITIET]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON_CHITIET](
	[maHD] [nvarchar](30) NOT NULL,
	[maSP] [nvarchar](30) NOT NULL,
	[tien] [float] NULL,
	[soLuong] [int] NULL,
 CONSTRAINT [PK_HOADON_CHITIET] PRIMARY KEY CLUSTERED 
(
	[maHD] ASC,
	[maSP] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[HOADON_CHITIET] ([maHD], [maSP], [tien], [soLuong]) VALUES (N'HD0001', N'SP001', 20000, 1)
INSERT [dbo].[HOADON_CHITIET] ([maHD], [maSP], [tien], [soLuong]) VALUES (N'HD0001', N'SP002', 20000, 2)
/****** Object:  StoredProcedure [dbo].[sp_FindMonthSoLuongChi]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindMonthSoLuongChi] (@Month nvarchar(30),@Year nvarchar(30))

AS
BEGIN
	SELECT DISTINCT QLSP.tenSP, QLSP.soLuong, QLSP.ngay
FROM SANPHAM SP JOIN QLSANPHAM QLSP
ON SP.maQLSP = SP.maQLSP
WHERE MONTH(QLSP.ngay)= @Month and YEAR(QLSP.ngay)=@Year
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindMonthChi]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindMonthChi] (@Month nvarchar(30),@Year nvarchar(30))
AS
BEGIN
	SELECT 
SP.tenSP, QLSP.ngay, SP.giaBan
	FROM SANPHAM SP JOIN QLSANPHAM QLSP
	ON SP.maQLSP = QLSP.maQLSP
	WHERE MONTH(QLSP.ngay)= @Month and YEAR(QLSP.ngay)=@Year
	GROUP BY SP.tenSP, QLSP.ngay, SP.giaBan
	
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindSoLuongChi_getTenSP]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[sp_FindSoLuongChi_getTenSP] (@sp nvarchar(50))
AS
BEGIN
	SELECT DISTINCT QLSP.tenSP, QLSP.soLuong, QLSP.ngay
FROM SANPHAM SP JOIN QLSANPHAM QLSP
ON SP.maQLSP = SP.maQLSP
WHERE QLSP.tenSP = @sp
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindYearSoLuongChi]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindYearSoLuongChi] (@Year nvarchar(30))
AS
BEGIN
	
	SELECT DISTINCT QLSP.tenSP, QLSP.soLuong, QLSP.ngay
FROM SANPHAM SP JOIN QLSANPHAM QLSP
ON SP.maQLSP = SP.maQLSP
Where @Year = YEAR(QLSP.ngay)
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindYearChi]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindYearChi] (@Year nvarchar(30))
AS
BEGIN
	SELECT
SP.tenSP, QLSP.ngay, SP.giaBan
	FROM SANPHAM SP JOIN QLSANPHAM QLSP
	ON SP.maQLSP = QLSP.maQLSP
	Where @Year = YEAR(QLSP.ngay)
	GROUP BY SP.tenSP, QLSP.ngay, SP.giaBan
END
GO
/****** Object:  StoredProcedure [dbo].[sp_SelectAllChi]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_SelectAllChi]
AS
BEGIN
	SELECT SP.tenSP, QLSP.ngay, SP.giaBan
FROM SANPHAM SP JOIN QLSANPHAM QLSP
ON SP.maQLSP = QLSP.maQLSP
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindDayChi]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindDayChi] (@Day nvarchar(30),@Month nvarchar(30), @Year nvarchar(30))
AS
BEGIN
SELECT
SP.tenSP, QLSP.ngay, SP.giaBan
	FROM SANPHAM SP JOIN QLSANPHAM QLSP
	ON SP.maQLSP = QLSP.maQLSP
	WHERE DAY(QLSP.ngay)=@Day and MONTH(QLSP.ngay)=@Month and YEAR(QLSP.ngay)=@Year
	GROUP BY SP.tenSP, QLSP.ngay, SP.giaBan
END
GO
/****** Object:  StoredProcedure [dbo].[sp_SelectSoLuongNhap]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_SelectSoLuongNhap]

AS
BEGIN
select DISTINCT SP.tenSP, QLSP.soLuong, QLSP.ngay
from QLSANPHAM QLSP join SANPHAM SP
on QLSP.maQLSP = SP.maQLSP
END
GO
/****** Object:  StoredProcedure [dbo].[sp_SelectSoLuongBanRa]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_SelectSoLuongBanRa]
AS
BEGIN
	select DISTINCT SP.tenSP, HDCT.soLuong, HD.ngayLap
from HOADON_CHITIET HDCT join HOADON HD 
on HDCT.maHD = HD.maHD join SANPHAM SP on HDCT.maSP = SP.maSP
END
GO
/****** Object:  StoredProcedure [dbo].[sp_SelectAllDoanhThu]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_SelectAllDoanhThu]
AS BEGIN
	SELECT SP.tenSP,HD.ngayLap, HDCT.tien
from HOADON HD join HOADON_CHITIET HDCT
on HD.maHD = HDCT.maHD join SANPHAM SP on HDCT.maSP = SP.maSP
GROUP BY SP.tenSP,HD.ngayLap, HDCT.tien
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindYearThu]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindYearThu] (@Year nvarchar(30))
	
AS
BEGIN
SELECT
	
	SP.tenSP, HD.ngayLap, HDCT.tien
	FROM HOADON HD JOIN HOADON_CHITIET HDCT
	ON HD.maHD = HDCT.maHD join SANPHAM SP on HDCT.maSP = SP.maSP
	Where @Year = YEAR(HD.ngayLap)
	GROUP BY SP.tenSP, HD.ngayLap, HDCT.tien
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindYearSoLuongThu]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindYearSoLuongThu] (@Year nvarchar(30))
AS
BEGIN
	SELECT DISTINCT SP.tenSP, HDCT.soLuong, HD.ngayLap
FROM HOADON HD JOIN HOADON_CHITIET HDCT
ON HD.maHD = HDCT.maHD JOIN SANPHAM SP
ON HDCT.maSP = SP.maSP
WHERE @Year = YEAR(HD.ngayLap)
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindSoLuongThu_getTenSP]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindSoLuongThu_getTenSP] (@sp nvarchar(50))
AS
BEGIN
	select SP.tenSP, HDCT.soLuong, HD.ngayLap
from HOADON HD JOIN HOADON_CHITIET HDCT
on HD.maHD = HDCT.maHD JOIN SANPHAM SP
ON HDCT.maSP = SP.maSP
where SP.tenSP = @sp
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindMonthThu]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindMonthThu] (@Month nvarchar(30),@Year nvarchar(30))
AS
BEGIN
	SELECT 
SP.tenSP, HD.ngayLap, HDCT.tien
	FROM HOADON HD JOIN HOADON_CHITIET HDCT
	ON HD.maHD = HDCT.maHD join SANPHAM SP on HDCT.maSP = SP.maSP
	WHERE MONTH(HD.ngayLap)= @Month and YEAR(HD.ngayLap)=@Year
	GROUP BY SP.tenSP, HD.ngayLap, HDCT.tien
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindMonthSoLuongThu]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindMonthSoLuongThu] (@Month nvarchar(30),@Year nvarchar(30))

AS
BEGIN
	select DISTINCT SP.tenSP, HDCT.soLuong, HD.ngayLap
from HOADON HD JOIN HOADON_CHITIET HDCT
on HD.maHD = HDCT.maHD JOIN SANPHAM SP
ON HDCT.maSP = SP.maSP
WHERE MONTH(HD.ngayLap)= @Month and YEAR(HD.ngayLap)=@Year
END
GO
/****** Object:  StoredProcedure [dbo].[sp_FindDayThu]    Script Date: 12/10/2020 15:52:09 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_FindDayThu] (@Day nvarchar(30),@Month nvarchar(30), @Year nvarchar(30))
AS
BEGIN
SELECT
	SP.tenSP, HD.ngayLap, HDCT.tien
	FROM HOADON HD JOIN HOADON_CHITIET HDCT
	ON HD.maHD = HDCT.maHD join SANPHAM SP on HDCT.maSP = SP.maSP
	WHERE DAY(HD.ngayLap)=@Day and MONTH(HD.ngayLap)=@Month and YEAR(HD.ngayLap)=@Year
	GROUP BY SP.tenSP, HD.ngayLap, HDCT.tien
END
GO
/****** Object:  ForeignKey [FK_QLSANPHAM_NHACUNGCAP]    Script Date: 12/10/2020 15:52:09 ******/
ALTER TABLE [dbo].[QLSANPHAM]  WITH CHECK ADD  CONSTRAINT [FK_QLSANPHAM_NHACUNGCAP] FOREIGN KEY([maNCC])
REFERENCES [dbo].[NHACUNGCAP] ([maNCC])
GO
ALTER TABLE [dbo].[QLSANPHAM] CHECK CONSTRAINT [FK_QLSANPHAM_NHACUNGCAP]
GO
/****** Object:  ForeignKey [FK_HOADON_NHANVIEN]    Script Date: 12/10/2020 15:52:09 ******/
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_NHANVIEN] FOREIGN KEY([maNV])
REFERENCES [dbo].[NHANVIEN] ([maNV])
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [FK_HOADON_NHANVIEN]
GO
/****** Object:  ForeignKey [FK_TAIKHOAN_NHANVIEN]    Script Date: 12/10/2020 15:52:09 ******/
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD  CONSTRAINT [FK_TAIKHOAN_NHANVIEN] FOREIGN KEY([maNV])
REFERENCES [dbo].[NHANVIEN] ([maNV])
GO
ALTER TABLE [dbo].[TAIKHOAN] CHECK CONSTRAINT [FK_TAIKHOAN_NHANVIEN]
GO
/****** Object:  ForeignKey [FK_TAIKHOAN_PHANLOAI_TAIKHOAN]    Script Date: 12/10/2020 15:52:09 ******/
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD  CONSTRAINT [FK_TAIKHOAN_PHANLOAI_TAIKHOAN] FOREIGN KEY([maLTK])
REFERENCES [dbo].[PHANLOAI_TAIKHOAN] ([maLTK])
GO
ALTER TABLE [dbo].[TAIKHOAN] CHECK CONSTRAINT [FK_TAIKHOAN_PHANLOAI_TAIKHOAN]
GO
/****** Object:  ForeignKey [FK_SANPHAM_QLSANPHAM]    Script Date: 12/10/2020 15:52:09 ******/
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD  CONSTRAINT [FK_SANPHAM_QLSANPHAM] FOREIGN KEY([maQLSP])
REFERENCES [dbo].[QLSANPHAM] ([maQLSP])
GO
ALTER TABLE [dbo].[SANPHAM] CHECK CONSTRAINT [FK_SANPHAM_QLSANPHAM]
GO
/****** Object:  ForeignKey [FK_HOADON_CHITIET_HOADON]    Script Date: 12/10/2020 15:52:09 ******/
ALTER TABLE [dbo].[HOADON_CHITIET]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_CHITIET_HOADON] FOREIGN KEY([maHD])
REFERENCES [dbo].[HOADON] ([maHD])
GO
ALTER TABLE [dbo].[HOADON_CHITIET] CHECK CONSTRAINT [FK_HOADON_CHITIET_HOADON]
GO
/****** Object:  ForeignKey [FK_HOADON_CHITIET_SANPHAM]    Script Date: 12/10/2020 15:52:09 ******/
ALTER TABLE [dbo].[HOADON_CHITIET]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_CHITIET_SANPHAM] FOREIGN KEY([maSP])
REFERENCES [dbo].[SANPHAM] ([maSP])
GO
ALTER TABLE [dbo].[HOADON_CHITIET] CHECK CONSTRAINT [FK_HOADON_CHITIET_SANPHAM]
GO
