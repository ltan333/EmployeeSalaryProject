package com.anlee.employeemanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ann
 */
public class NhanVien {

    private String maNV;
    private String hoTen;
    private ChucVu chucVu;
    private String gioiTinh;
    private String email;
    private String sdt;
    private String diaChi;
    private Date ngayVaoLam;
    private double heSoLuong;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, ChucVu chucVu, String gioiTinh, String email, String sdt, String diaChi, Date ngayVaoLam, double heSoLuong) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.ngayVaoLam = ngayVaoLam;
        this.heSoLuong = heSoLuong;
    }
    
    public NhanVien(String maNV, String hoTen, ChucVu chucVu, String gioiTinh, String email, String sdt, String diaChi, String ngayVaoLam, double heSoLuong) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.setNgayVaoLam(ngayVaoLam);
        this.heSoLuong = heSoLuong;
    }
    
    

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }


    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChucVu() {
        return chucVu.getChucVu();
    }
    
    public ChucVu getChucVuObj() {
        return chucVu;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");

    public String getNgayVaoLam() {
        return s.format(ngayVaoLam);
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        try {
            this.ngayVaoLam = s.parse(ngayVaoLam);
        } catch (ParseException ex) {
            this.ngayVaoLam = null;
        }
    }
    
    public void setNgayVaoLamDate(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

}
