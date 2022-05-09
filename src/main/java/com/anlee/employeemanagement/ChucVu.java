
package com.anlee.employeemanagement;

/**
 *
 * @author Ann
 */
public class ChucVu {
    private String maChucVu;
    private String chucVu;
    private double luong;

    public ChucVu() {
    }

    public ChucVu(String maChucVu, String chucVu, double luong) {
        this.maChucVu = maChucVu;
        this.chucVu = chucVu;
        this.luong = luong;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }
    

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }
    
    
}
