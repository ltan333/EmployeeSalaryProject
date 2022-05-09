package com.anlee.employeemanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ann
 */
public class MainSceneController implements Initializable {

    @FXML
    private AnchorPane anchorPaneTable;

    TableView table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void start() {
        TableView tableView = new TableView();
        tableView.setPlaceholder(new Label("Không tìm thây!"));
        tableView.prefWidthProperty().bind(anchorPaneTable.widthProperty());
        tableView.prefHeightProperty().bind(anchorPaneTable.heightProperty());
        TableColumn<NhanVien, String> column1 = new TableColumn<>("Roll Number");
        column1.setStyle("-fx-alignment: CENTER;");

        column1.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
        
    }

    public static void main(String[] args) {
        System.out.println("tr?ng");
    }

}
