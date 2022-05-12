/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anlee.employeemanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ann
 */
public class PopupAddChucVuController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private TextField chucVuField;

    @FXML
    private Label label;

    @FXML
    private TextField maChucVuField;

    @FXML
    private TextField salaryField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addBtn.setOnAction((e) -> {
            if (InputValidation.isEmptyString(maChucVuField.getText())) {
                CreateMessBox.popupBoxMess("Mã Chức Vụ Không Thể Trống!", 2);
                return;
            }
            if (InputValidation.checkMaxStringLength(maChucVuField.getText(), 10)) {
                CreateMessBox.popupBoxMess("Mã Chức Vụ Không Thể Dài Hơn 10 Ký Tự!", 2);
                return;
            }
            if (DatabaseHandle.kiemTraMaChucVu(maChucVuField.getText())){
                CreateMessBox.popupBoxMess("Mã Chức Vụ Ðã Tồn Tại!", 2);
                return;
            }
            if (InputValidation.isEmptyString(chucVuField.getText())) {
                CreateMessBox.popupBoxMess("Chức Vụ Không Thể Trống!", 2);
                return;
            }
            if (InputValidation.checkMaxStringLength(maChucVuField.getText(), 40)) {
                CreateMessBox.popupBoxMess("Chức Vụ Không Thể Dài Hơn 40 Ký Tự!", 2);
                return;
            }
            if (!InputValidation.isFloat(salaryField.getText())) {
                if (Float.parseFloat(salaryField.getText()) <= 0) {
                    CreateMessBox.popupBoxMess("Số Lương Không Hợp Lệ!", 2);
                    return;
                }
                CreateMessBox.popupBoxMess("Số Lương Không Hợp Lệ!", 2);
                return;

            }
            if (Float.parseFloat(salaryField.getText()) > 900000000) {
                CreateMessBox.popupBoxMess("Số Lương Không Thể Lơn Hơn 9 Tỷ VNĐ!", 2);
                return;
            }
            ChucVu cv = new  ChucVu(maChucVuField.getText(), chucVuField.getText(), Float.parseFloat(salaryField.getText()));
            if(DatabaseHandle.addChucVu(cv)){
                CreateMessBox.popupBoxMess("Thêm Thành Công", 1);
                Stage stage = (Stage)addBtn.getScene().getWindow();
                stage.close();
            }
        });

    }

}
