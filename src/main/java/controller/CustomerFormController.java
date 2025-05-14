package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableView tblCustomerView;

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

    List<Customer> customerList = new ArrayList<>();

    @FXML
    void btnAddOnAction(ActionEvent event){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
            preparedStatement.setInt(1,Integer.parseInt(txtID.getText()));
            preparedStatement.setString(2,txtName.getText());
            preparedStatement.setString(3,txtAddress.getText());
            preparedStatement.setDouble(4,Double.parseDouble(txtSalary.getText()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
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
            Connection connection = DBConnection.getInstance().getConnection(); 
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM customer");

            while(resultSet.next()) {
                customerList.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4)));
            }

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

//            ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
//
//            customerList.forEach(customer -> {
//                customerObservableList.add(customer);
//            });
//
//            tblCustomerView.setItems(customerObservableList);

            tblCustomerView.setItems(FXCollections.observableArrayList(customerList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
