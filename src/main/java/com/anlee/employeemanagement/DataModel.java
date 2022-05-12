/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anlee.employeemanagement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ann
 */
public class DataModel {

    public static ObservableList<NhanVien> nhanVienList = FXCollections.observableArrayList();
    public static NhanVien nhanVien;
    public static ChucVu chucVu;

    public static void setNhanVien(NhanVien nv) {
        nhanVien = nv;
        nhanVienList.clear();
        nhanVienList.add(nv);
    }

    public static void setChucVu(ChucVu cv) {
        chucVu = cv;
    }

}
