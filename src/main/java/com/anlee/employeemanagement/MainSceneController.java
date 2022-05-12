package com.anlee.employeemanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class MainSceneController implements Initializable {

    @FXML
    private Button addEmployeeBtn;

    @FXML
    private Button addChucVuBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private Tab tabPaneChucVu;

    @FXML
    private Tab tabPaneEployee;

    @FXML
    private TextField searchField;

    TableView table;
    ArrayList<CheckBox> checkBoxChucVu = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        taoGiaTriBangNhanVien("");
        tabPaneEployee.setOnSelectionChanged((e) -> {
            taoGiaTriBangNhanVien(searchField.getText());
        });
        tabPaneChucVu.setOnSelectionChanged((e) -> {
            taoGiaTriBangChucVu(searchField.getText());
        });
        addEmployeeBtn.setOnAction((t) -> {
            showDialogThemNhanVien();
        });
        resetBtn.setOnAction((t) -> {
            if (tabPaneEployee.isSelected()) {
                taoGiaTriBangNhanVien(searchField.getText());
            } else {
                taoGiaTriBangChucVu(searchField.getText());
            }
        });
        searchField.setOnKeyReleased((e) -> {
            if (tabPaneEployee.isSelected()) {
                taoGiaTriBangNhanVien(searchField.getText());
            } else {
                taoGiaTriBangChucVu(searchField.getText());
            }
        });
        addChucVuBtn.setOnAction((e) -> {
            showDialogThemChucVu();
        });

    }

    public void taoFormBangNhanVien() {
        TableView tableView = new TableView();
        tableView.setPlaceholder(new Label("Không tìm thây!"));
//        tableView.prefWidthProperty().bind(tabPaneEployee.get());
//        tableView.prefHeightProperty().bind(tabPaneEployee.heightProperty());

        TableColumn<NhanVien, String> column1 = new TableColumn<>("Mã Nhân Viên");
        column1.setStyle("-fx-alignment: CENTER;");
        column1.setCellValueFactory(new PropertyValueFactory<>("maNV"));

        TableColumn<NhanVien, String> column2 = new TableColumn<>("Họ Tên");
        column2.setStyle("-fx-alignment: CENTER;");
        column2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.13));
        column2.setCellValueFactory(new PropertyValueFactory<>("hoTen"));

        TableColumn<NhanVien, String> column3 = new TableColumn<>("Giới Tính");
        column3.setStyle("-fx-alignment: CENTER;");
        column3.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));

        TableColumn<NhanVien, String> column4 = new TableColumn<>("Chức Vụ");
        column4.setStyle("-fx-alignment: CENTER;");
        column4.setCellValueFactory(new PropertyValueFactory<>("chucVu"));

        TableColumn<NhanVien, Double> column5 = new TableColumn<>("Hệ Số Lương");
        column5.setStyle("-fx-alignment: CENTER;");
        column5.setCellValueFactory(new PropertyValueFactory<>("heSoLuong"));
        column5.setCellFactory(cb -> new TableCell<NhanVien, Double>() {
            @Override
            protected void updateItem(Double luong, boolean empty) {
                super.updateItem(luong, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%,.2f", luong));
                }
            }

        });

        TableColumn<NhanVien, String> column6 = new TableColumn<>("Ngày Vào Làm");
        column6.setStyle("-fx-alignment: CENTER;");
        column6.setCellValueFactory(new PropertyValueFactory<>("ngayVaoLam"));

        TableColumn<NhanVien, String> column7 = new TableColumn<>("Email");
        column7.setStyle("-fx-alignment: CENTER;");
        column7.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<NhanVien, String> column8 = new TableColumn<>("SÐT");
        column8.setStyle("-fx-alignment: CENTER;");
        column8.setCellValueFactory(new PropertyValueFactory<>("sdt"));

        TableColumn<NhanVien, String> column9 = new TableColumn<>("Địa Chỉ");
        column9.setStyle("-fx-alignment: CENTER;");
        column9.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        column9.setCellValueFactory(new PropertyValueFactory<>("diaChi"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
        tableView.getColumns().add(column7);
        tableView.getColumns().add(column8);
        tableView.getColumns().add(column9);

//        tableView.getItems().add(new NhanVien("CODE123", "Le Truong An", new ChucVu("TK", "Thu ky", 8000000), "Nam", "anltce@fpt.edu.vn", "0388833303", "Tan Luoc, Binh Tan, Vinh Long", "23/3/2020", 1.5));
        table = tableView;
    }

    public void taoGiaTriBangNhanVien(String keySearch) {
        taoFormBangNhanVien();
        for (NhanVien nv : DatabaseHandle.getAllNhanVien()) {
            if (nv.getMaNV().toLowerCase().contains(keySearch.toLowerCase()) || nv.getHoTen().toLowerCase().contains(keySearch.toLowerCase()) || nv.getChucVu().toLowerCase().contains(keySearch.toLowerCase()) || nv.getDiaChi().toLowerCase().contains(keySearch.toLowerCase()) || nv.getEmail().toLowerCase().contains(keySearch.toLowerCase()) || nv.getSdt().toLowerCase().contains(keySearch.toLowerCase()) || nv.getNgayVaoLam().toLowerCase().contains(keySearch.toLowerCase())) {
                table.getItems().add(nv);
            }
        }
        //Event click chuot vào nhân viên
        table.setRowFactory((tableview) -> {
            final TableRow<NhanVien> row = new TableRow<>();
            final ContextMenu rowMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Delete");
            MenuItem editItem = new MenuItem("Edit");
            //Xóa nhân viên
            removeItem.setOnAction(e -> {
                if (CreateMessBox.popupChoose("Bạn Chắn Chắn Chứ?")) {
                    DatabaseHandle.xoaNhanVien(row.getItem());
                    table.getItems().remove(row.getItem());
                }
            });
            //Sua nhân viên
            editItem.setOnAction((e) -> {
                DataModel.setNhanVien(row.getItem());
                showDialogSuaNhanVien();
            });

            rowMenu.getItems().addAll(editItem, removeItem);
            row.contextMenuProperty().bind(
                    Bindings.when(Bindings.isNotNull(row.itemProperty()))
                            .then(rowMenu)
                            .otherwise((ContextMenu) null));

            //Event double click
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ChucVu cv = DatabaseHandle.findChucVu(row.getItem().getChucVu());
                    CreateMessBox.popupBoxMessContent("Tính Lương Nhân Viên " + row.getItem().getHoTen(), "Chức Vụ: " + cv.getChucVu() + "\n"
                            + "Lương Cơ Bản: " + String.format("%,.0f", cv.getLuong()) + "\n"
                            + "Hệ Số Lương: " + String.format("%,.2f", row.getItem().getHeSoLuong()) + "\n"
                            + "Thuế TNCT: " + String.format("%,.0f", tinhThueThuNhapCaNhan(row.getItem())) + "\n"
                            + "Tổng Lương: " + String.format("%,.0f", (cv.getLuong() * row.getItem().getHeSoLuong() - tinhThueThuNhapCaNhan(row.getItem()))), 1);
                }
            }
            );

            return row;
        });
        tabPaneEployee.setContent(table);
    }

    public void taoFormBangChucVu() {
        TableView tableView = new TableView();
        tableView.setPlaceholder(new Label("Không tìm thây!"));
//        tableView.prefWidthProperty().bind(tabPaneEployee.get());
//        tableView.prefHeightProperty().bind(tabPaneEployee.heightProperty());

        TableColumn<ChucVu, String> column1 = new TableColumn<>("Mã Chức Vụ");
        column1.setStyle("-fx-alignment: CENTER;");
        column1.setCellValueFactory(new PropertyValueFactory<>("maChucVu"));

        TableColumn<ChucVu, String> column2 = new TableColumn<>("Tên Chức Vụ");
        column2.setStyle("-fx-alignment: CENTER;");
        column2.setCellValueFactory(new PropertyValueFactory<>("chucVu"));

        TableColumn<ChucVu, Double> column3 = new TableColumn<>("Lương");
        column3.setStyle("-fx-alignment: CENTER;");
        column3.setCellValueFactory(new PropertyValueFactory<>("luong"));
        column3.setCellFactory(tc -> new TableCell<ChucVu, Double>() {

            @Override
            protected void updateItem(Double luong, boolean empty) {
                super.updateItem(luong, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%,.0f", luong));
                }
            }
        });

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

//        tableView.getItems().add(new NhanVien("CODE123", "Le Truong An", new ChucVu("TK", "Thu ky", 8000000), "Nam", "anltce@fpt.edu.vn", "0388833303", "Tan Luoc, Binh Tan, Vinh Long", "23/3/2020", 1.5));
        table = tableView;
    }

    public void taoGiaTriBangChucVu(String keySearch) {
        taoFormBangChucVu();
        for (ChucVu cv : DatabaseHandle.getAllChucVu()) {
            if (cv.getMaChucVu().toLowerCase().contains(keySearch.toLowerCase()) || cv.getChucVu().toLowerCase().contains(keySearch.toLowerCase())) {
                table.getItems().add(cv);
            }
        }
        table.setRowFactory((p) -> {
            TableRow<ChucVu> row = new TableRow<>();
            ContextMenu rowMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("Edit");
            MenuItem delItem = new MenuItem("Delete");
            rowMenu.getItems().addAll(editItem, delItem);
            delItem.setOnAction((t) -> {
                for (NhanVien nv : DatabaseHandle.getAllNhanVien()) {
                    if (nv.getChucVuObj().getMaChucVu().equalsIgnoreCase(row.getItem().getMaChucVu())) {
                        CreateMessBox.popupBoxMess("Không Thể Xóa Vì Vẫn Còn Nhân Viên Có Chức Vụ Này!", 2);
                        return;
                    }
                }
                if (CreateMessBox.popupChoose("Bạn Chắn Chắn Chứ?")) {
                    DatabaseHandle.delChucVu(row.getItem());
                    table.getItems().remove(row.getItem());
                }
            });
            editItem.setOnAction((e) -> {
                DataModel.setChucVu(row.getItem());
                showDialogSuaChucVu();
            });
            row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty()))
                    .then(rowMenu)
                    .otherwise((ContextMenu) null));

            return row;

        });
        tabPaneChucVu.setContent(table);
    }

    public void showDialogThemNhanVien() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PopupAddEmployee.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Add Employee");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showDialogSuaNhanVien() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PopupEditEmployee.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Update Employee");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showDialogThemChucVu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PopupAddChucVu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Thêm Chức Vụ");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showDialogSuaChucVu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PopupEditChucVu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Sửa Chức Vụ");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private double tinhThueThuNhapCaNhan(NhanVien nv) {
        double total = 0;
        double thuNhap = nv.getHeSoLuong() * DatabaseHandle.findChucVu(nv.getChucVu()).getLuong();
        if (thuNhap <= 5000000) {
            total = thuNhap * 5 / 100;
        } else if (thuNhap > 5000000 && thuNhap <= 10000000) {
            total = (thuNhap * 10 / 100) - 250000;
        } else if (thuNhap > 10000000 && thuNhap <= 18000000) {
            total = (thuNhap * 15 / 100) - 750000;
        } else if (thuNhap > 18000000 && thuNhap <= 32000000) {
            total = (thuNhap * 20 / 100) - 1650000;
        } else if (thuNhap > 32000000 && thuNhap <= 52000000) {
            total = (thuNhap * 25 / 100) - 3250000;
        } else if (thuNhap > 52000000 && thuNhap <= 80000000) {
            total = (thuNhap * 30 / 100) - 5850000;
        } else if (thuNhap > 80000000) {
            total = (thuNhap * 35 / 100)-9850000;
        }

        return total;
    }

}
