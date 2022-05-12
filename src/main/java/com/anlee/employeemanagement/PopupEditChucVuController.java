
package com.anlee.employeemanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ann
 */
public class PopupEditChucVuController implements Initializable{
    
    @FXML
    private TextField chucVuField;

    @FXML
    private TextField maChucVuField;

    @FXML
    private Button okBtn;

    @FXML
    private TextField salaryField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maChucVuField.setText(DataModel.chucVu.getMaChucVu());
        chucVuField.setText(DataModel.chucVu.getChucVu());
        salaryField.setText(DataModel.chucVu.getLuong()+"");
        
        okBtn.setOnAction((e) -> {
            
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
            if(DatabaseHandle.updateChucVu(cv)){
                CreateMessBox.popupBoxMess("Thêm Thành Công", 1);
                Stage stage = (Stage)okBtn.getScene().getWindow();
                stage.close();
            }
        });
        
    }
}
