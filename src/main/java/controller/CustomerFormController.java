package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFormController {

    @FXML
    private Button BtnDelete;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnReload;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadTable(){
        try {
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/thoga_kade","root","1234");
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM customer");

            while(resultSet.next())
                System.out.println(resultSet.getString(2));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
