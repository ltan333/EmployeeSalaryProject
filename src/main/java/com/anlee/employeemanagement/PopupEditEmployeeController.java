
package com.anlee.employeemanagement;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class PopupEditEmployeeController implements Initializable {

    @FXML
    private TextField addressField;

    @FXML
    private ChoiceBox choiceBoxChucVu;

    @FXML
    private Label label;

    @FXML
    private TextField maNvField;

    @FXML
    private TextField meowField;

    @FXML
    private TextField nameField;

    @FXML
    private Button okBtn;

    @FXML
    private TextField phoneField;

    @FXML
    private RadioButton radioGenderFemale;

    @FXML
    private RadioButton radioGenderMale;

    @FXML
    private RadioButton radioGenderUnknown;

    @FXML
    private TextField rateField;

    
    ToggleGroup genderRadioGroup = new ToggleGroup();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addValueCheckboxChucVu();
        start();

        
    }

    public void start() {
        radioGenderFemale.setToggleGroup(genderRadioGroup);
        radioGenderMale.setToggleGroup(genderRadioGroup);
        radioGenderUnknown.setToggleGroup(genderRadioGroup);
        if (DataModel.nhanVien.getGioiTinh().equalsIgnoreCase("Nam")) {
            radioGenderMale.setSelected(true);
        }
        if (DataModel.nhanVien.getGioiTinh().equalsIgnoreCase("Nữ")) {
            radioGenderFemale.setSelected(true);
        }
        if (DataModel.nhanVien.getGioiTinh().equalsIgnoreCase("Khác")) {
            radioGenderUnknown.setSelected(true);
        }
        
        maNvField.setText(DataModel.nhanVien.getMaNV());
        nameField.setText(DataModel.nhanVien.getHoTen());
        phoneField.setText(DataModel.nhanVien.getSdt());
        meowField.setText(DataModel.nhanVien.getEmail());
        rateField.setText(DataModel.nhanVien.getHeSoLuong()+"");
        addressField.setText(DataModel.nhanVien.getDiaChi());
        maNvField.setText(DataModel.nhanVien.getMaNV());
        choiceBoxChucVu.setValue(DataModel.nhanVien.getChucVu());
        okBtn.setOnAction((t) -> {
//            System.out.println((choiceBoxChucVu.getSelectionModel().getSelectedItem().toString()));
            
            

            if (InputValidation.isEmptyString(nameField.getText())) {
                CreateMessBox.popupBoxMess("Ten Nhan Vien Khong The Trong", 2);
                return;
            }

            if (InputValidation.checkMaxStringLength(nameField.getText(), 40)) {
                CreateMessBox.popupBoxMess("Ten Nhan Vien Khong The Qua Dai", 2);
                return;
            }

            if (!InputValidation.isPhoneNumber(phoneField.getText())) {
                CreateMessBox.popupBoxMess("So Dien Thoai Khong Hop Le", 2);
                return;
            }

            if (!InputValidation.isEmail(meowField.getText())) {
                CreateMessBox.popupBoxMess("Email Khong Hop Le", 2);
                return;
            }

            if (InputValidation.isEmptyString(rateField.getText())) {
                CreateMessBox.popupBoxMess("He So Luong Khong The Trong", 2);
                return;
            }
            if (!InputValidation.isFloat(rateField.getText())) {
                if (Float.parseFloat(rateField.getText()) <= 0) {
                    CreateMessBox.popupBoxMess("He So Luong Khong Hop Le", 2);
                    return;
                }
                CreateMessBox.popupBoxMess("He So Luong Khong Hop Le", 2);
                return;
            }
            if(choiceBoxChucVu.getSelectionModel().getSelectedItem().toString().strip().equalsIgnoreCase("Chon Chuc Vu")){
                CreateMessBox.popupBoxMess("Vui Long Chon Chuc Vu", 2);
                return;
            }
            RadioButton radio = (RadioButton)genderRadioGroup.getSelectedToggle();
            ChucVu cv = DatabaseHandle.findChucVu(choiceBoxChucVu.getSelectionModel().getSelectedItem().toString());
            NhanVien nv = new NhanVien();
            nv.setMaNV(maNvField.getText());
            nv.setHoTen(nameField.getText());
            nv.setSdt(phoneField.getText());
            nv.setHeSoLuong(Double.parseDouble(rateField.getText()));
            nv.setEmail(meowField.getText());
            nv.setDiaChi(addressField.getText());
            nv.setGioiTinh(radio.getText());
            nv.setChucVu(cv);
            nv.setNgayVaoLamDate(Calendar.getInstance().getTime());
            if(DatabaseHandle.updateNhanVien(nv)){
                CreateMessBox.popupBoxMess("Sửa Thành Công", 1);
                Stage stage = (Stage)okBtn.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void addValueCheckboxChucVu() {
        choiceBoxChucVu.setValue("Chon Chuc Vu");
        for (ChucVu c : DatabaseHandle.getAllChucVu()) {
            choiceBoxChucVu.getItems().add(c.getChucVu());
        }
    }

}
