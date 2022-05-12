package com.anlee.employeemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author truon
 */
public class DatabaseHandle {

    private static final String dbName = "EmployeeSalary";
    private static final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=" + dbName + ";integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private static final String userName = "sa";
    private static final String pass = "1234563";

    public static Connection getConnection() {
        try {
//            String url = "jdbc:sqlserver://localhost;databaseName=TestDB;sslProtocol=TLSv1";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(connectionUrl, userName, pass);
            return conn;
        } catch (Exception e) {
            System.out.println("Err");
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> c = new ArrayList<>();
        Connection conn = getConnection();
        String query = "SELECT * FROM nhanVien";
        try {
            PreparedStatement stament = conn.prepareStatement(query);
            ResultSet rs = stament.executeQuery();

            while (rs.next()) {
                c.add(new NhanVien(rs.getString(1), rs.getString(2), getChucVu(rs.getString(3)), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9)));
            }
            stament.close();
            conn.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ChucVu getChucVu(String maChucVu) {
        Connection conn = getConnection();
        String query = "SELECT * FROM chucVu";
        ChucVu cv = new ChucVu();
        try {
            PreparedStatement stament = conn.prepareStatement(query);
            ResultSet rs = stament.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).strip().equalsIgnoreCase(maChucVu.strip())) {
                    cv.setMaChucVu(rs.getString(1));
                    cv.setChucVu(rs.getString(2));
                    cv.setLuong(rs.getFloat(3));
                }
            }
            stament.close();
            conn.close();
            return cv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<ChucVu> getAllChucVu() {
        ArrayList<ChucVu> c = new ArrayList<>();
        Connection conn = getConnection();
        String query = "SELECT * FROM chucVu";
        try {
            PreparedStatement stament = conn.prepareStatement(query);
            ResultSet rs = stament.executeQuery();

            while (rs.next()) {
                c.add(new ChucVu(rs.getString(1), rs.getString(2), rs.getFloat(3)));
            }
            stament.close();
            conn.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean addChucVu(ChucVu cv) {
        Connection conn = getConnection();
        String sqlInsert = "INSERT INTO chucVu VALUES(?, ?, ?)";
        try {
            PreparedStatement stament = conn.prepareStatement(sqlInsert);
            stament.setString(1, cv.getMaChucVu());
            stament.setString(2, cv.getChucVu());
            stament.setFloat(3, Float.parseFloat(cv.getLuong()+""));

            stament.executeUpdate();
            stament.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean delChucVu(ChucVu cv){
        Connection conn = getConnection();
        String query = "DELETE FROM chucVu WHERE maChucVu=?";
        try {
            PreparedStatement stament = conn.prepareStatement(query);
            stament.setString(1, cv.getMaChucVu());

            stament.executeUpdate();
            stament.close();
            conn.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addNhanVien(NhanVien nv) {
        Connection conn = getConnection();
        String sqlInsert = "INSERT INTO nhanVien VALUES(?, ?, ?,?,?,?,?,?,?)";
        try {
            PreparedStatement stament = conn.prepareStatement(sqlInsert);
            stament.setString(1, nv.getMaNV());
            stament.setString(2, nv.getHoTen());
            stament.setString(3, nv.getChucVuObj().getMaChucVu());
            stament.setString(4, nv.getGioiTinh());
            stament.setString(5, nv.getEmail());
            stament.setString(6, nv.getSdt());
            if (nv.getDiaChi().isEmpty()) {
                stament.setString(7, "");
            } else {
                stament.setString(7, nv.getDiaChi());
            }
            stament.setString(8, nv.getNgayVaoLam());
            stament.setFloat(9, Float.parseFloat(nv.getHeSoLuong() + ""));

            stament.executeUpdate();
            stament.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean kiemTraMaNv(String maNv) {
        Connection conn = getConnection();
        String query = "SELECT maNV FROM nhanVien";
        try {
            PreparedStatement stament = conn.prepareStatement(query);
            ResultSet rs = stament.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).strip().equalsIgnoreCase(maNv.strip())) {
                    return true;
                }
            }
            stament.close();
            conn.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean kiemTraMaChucVu(String maCV) {
        Connection conn = getConnection();
        String query = "SELECT maChucVu FROM chucVu";
        try {
            PreparedStatement stament = conn.prepareStatement(query);
            ResultSet rs = stament.executeQuery();

            while (rs.next()) {
                if (rs.getString(1).strip().equalsIgnoreCase(maCV.strip())) {
                    return true;
                }
            }
            stament.close();
            conn.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ChucVu findChucVu(String chucvu) {
        Connection conn = getConnection();
        String query = "SELECT * FROM chucVu";
        ChucVu cv = new ChucVu();
        try {
            PreparedStatement stament = conn.prepareStatement(query);
            ResultSet rs = stament.executeQuery();

            while (rs.next()) {
                if (rs.getString(2).strip().equalsIgnoreCase(chucvu.strip())) {
                    cv.setMaChucVu(rs.getString(1));
                    cv.setChucVu(rs.getString(2));
                    cv.setLuong(rs.getFloat(3));
                }
            }
            stament.close();
            conn.close();
            return cv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean xoaNhanVien(NhanVien nv) {
        Connection conn = getConnection();
        String query = "DELETE FROM nhanVien WHERE maNV=?";
        try {
            PreparedStatement stament = conn.prepareStatement(query);
            stament.setString(1, nv.getMaNV());

            stament.executeUpdate();
            stament.close();
            conn.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateNhanVien(NhanVien nv) {
        Connection conn = getConnection();
        String sqlUpdate = "UPDATE nhanVien SET hoTen = ?, chucVu = ?, gioiTinh = ?, email=?, sdt=?, diaChi=?, heSoLuong=? where maNV=?";
        try {
            PreparedStatement stament = conn.prepareStatement(sqlUpdate);
            stament.setString(1, nv.getHoTen());
            stament.setString(2, nv.getChucVuObj().getMaChucVu());
            stament.setString(3, nv.getGioiTinh());
            stament.setString(4, nv.getEmail());
            stament.setString(5, nv.getSdt());
            if (nv.getDiaChi().isEmpty()) {
                stament.setString(6, "");
            } else {
                stament.setString(6, nv.getDiaChi());
            }
            stament.setFloat(7, Float.parseFloat(nv.getHeSoLuong() + ""));

            stament.setString(8, nv.getMaNV());

            stament.executeUpdate();
            stament.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateChucVu(ChucVu cv){
        Connection conn = getConnection();
        String sqlUpdate = "UPDATE chucVu SET chucVu = ?, luong = ? where maChucVu=?";
        try {
            PreparedStatement stament = conn.prepareStatement(sqlUpdate);
            stament.setString(1, cv.getChucVu());
            stament.setFloat(2, Float.parseFloat(cv.getLuong()+""));
            stament.setString(3, cv.getMaChucVu());

            stament.executeUpdate();
            stament.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
