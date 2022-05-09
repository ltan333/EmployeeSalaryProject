# Info
Project build by NetBeans13  
JDK 17.0.1  
SDK 18  
# SETUP DB
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='nhanVien' and xtype='U')  
CREATE TABLE nhanVien (  
maNV nvarchar(10) PRIMARY KEY,  
hoTen nvarchar(50) not null,  
chucVu nvarchar(50),  
gioiTinh nvarchar (10) not null,  
email nvarchar(50),  
sdt nvarchar(12),  
diaChi nvarchar(50),  
ngayVaoLam nvarchar(10) not null,  
heSoLuong float not null  
)  
GO  
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='chucVu' and xtype='U')  
CREATE TABLE chucVu (  
maChucVu nvarchar(10) PRIMARY KEY,  
chucVu nvarchar(50) not null,  
luong float  
)  
GO  
# Hoang200