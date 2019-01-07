package lk.ijse.student.bankManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.student.bankManagementSystem.dto.AccountTypeDTO;
import lk.ijse.student.bankManagementSystem.idGenarator.IDGenarator;
import lk.ijse.student.bankManagementSystem.proxy.ProxyHandeler;
import lk.ijse.student.bankManagementSystem.service.ServiceFactory;
import lk.ijse.student.bankManagementSystem.service.custom.AccountService;
import lk.ijse.student.bankManagementSystem.service.custom.AccountTypeService;
import  lk.ijse.student.bankManagementSystem.observer.Observer;

import java.net.URL;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.O;

public class LoanAndAccSettingController implements Initializable,Observer,Remote{

    @FXML
    private JFXTextField txtLoanID;

    @FXML
    private JFXTextField txtLoanType;

    @FXML
    private JFXTextField txtInterestCharge;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtMaxAmount;

    @FXML
    private JFXTextField txtAccID;

    @FXML
    private JFXTextField txtAccType;

    @FXML
    private JFXTextField txtInterest;

    @FXML
    private TableView<?> loanDetailsTable;

    @FXML
    private TableView<AccountTypeDTO> accDetailsTable;

    @FXML
    private JFXTextField txtMinAmouOf1stDep;

    @FXML
    private JFXTextField txtMinMonthSalary;

    @FXML
    private JFXComboBox<String> cmbCountOfOwners;

    AccountTypeService accountTypeService;
    AccountService accountService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            cmbCountOfOwners.getItems().addAll("01", "02", "03");
            accountTypeService = ProxyHandeler.getIstance().getSuperService(ServiceFactory.ServiceTypes.ACCOUNTTYPE);
            accountService=ProxyHandeler.getIstance().getSuperService(ServiceFactory.ServiceTypes.ACCOUNT);
            UnicastRemoteObject.exportObject(this,0);
            accountTypeService.register(this);
            accountService.register(this);
            getAllAccountType();
            loadTableToTXT();
            accountTypeIDLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void saveAccountType(MouseEvent mouseEvent) {
        String accType=txtAccType.getText();
        if (accType.matches("[A-Za-z]{1,50}")){
            String interest=txtInterest.getText();
            if (interest.matches("[0-9]{1,50}[.][0]{2}")){
                String minAmount=txtMinAmouOf1stDep.getText();
                if (minAmount.matches("[0-9]{1,50}[.][0]{2}")){

                    AccountTypeDTO accountTypeDTO=new AccountTypeDTO(txtAccID.getText(),txtAccType.getText(),
                            Double.parseDouble(txtInterest.getText()),Double.parseDouble(txtMinAmouOf1stDep.getText()),
                            Integer.parseInt(cmbCountOfOwners.getValue()));
                    try {
                        boolean isAdded=accountTypeService.addAccountType(accountTypeDTO);
                        if (isAdded){
                            Alert alert=new Alert(Alert.AlertType.INFORMATION,"added",ButtonType.OK);
                            alert.show();
                        }else {
                            Alert alert=new Alert(Alert.AlertType.INFORMATION,"faild",ButtonType.OK);
                            alert.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Min Amount of first Diposite, Somthing Mistake", ButtonType.OK);
                    a.showAndWait();
                }
            }else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Interest, Somthing Mistake", ButtonType.OK);
                a.showAndWait();
            }
        }else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Account Type, Somthing Mistake", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    public void updateAccountType(MouseEvent mouseEvent) throws Exception {

        String accType=txtAccType.getText();
        if (accType.matches("[A-Za-z]{1,50}")){
            String interest=txtInterest.getText();
            if (interest.matches("[0-9]{1,50}[.][0]{2}")){
                String minAmount=txtMinAmouOf1stDep.getText();
                if (minAmount.matches("[0-9]{1,50}[.][0]{2}")){

                    AccountTypeDTO typeDTO=new AccountTypeDTO();
                    typeDTO.setAccTypeID(txtAccID.getText());
                    accountTypeService.reserved(typeDTO);

                    AccountTypeDTO accountTypeDTO=new AccountTypeDTO(txtAccID.getText(),txtAccType.getText(),
                            Double.parseDouble(txtInterest.getText()),Double.parseDouble(txtMinAmouOf1stDep.getText()),
                            Integer.parseInt(cmbCountOfOwners.getValue()));
                    try {
                        boolean isUpdate= accountTypeService.updateAccountType(accountTypeDTO);
                        if ( isUpdate){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Updated..",ButtonType.OK);
                            alert.show();
                        }else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Update Faild..",ButtonType.OK);
                            alert.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Min Amount of first Diposite, Somthing Mistake", ButtonType.OK);
                    a.showAndWait();
                }
            }else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Interest, Somthing Mistake", ButtonType.OK);
                a.showAndWait();
            }
        }else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Account Type, Somthing Mistake", ButtonType.OK);
            a.showAndWait();
        }
    }


    @FXML
    public void searchAccountType(MouseEvent mouseEvent) {
        try {
            AccountTypeDTO accountTypeDTO=accountTypeService.searchAccountType(txtAccID.getText());
            if (accountTypeDTO !=null){
                txtAccID.setText(accountTypeDTO.getAccTypeID());
                txtAccType.setText(accountTypeDTO.getAccType());
                txtInterest.setText(Double.toString(accountTypeDTO.getRate()));
                txtMinAmouOf1stDep.setText(Double.toString(accountTypeDTO.getMinAmouOf1stDep()));
                cmbCountOfOwners.setValue(Integer.toString(accountTypeDTO.getCountOfOwners()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteAccountType(MouseEvent mouseEvent) throws Exception {

        AccountTypeDTO typeDTO=new AccountTypeDTO();
        typeDTO.setAccTypeID(txtAccID.getText());
        accountTypeService.reserved(typeDTO);

        AccountTypeDTO accountTypeDTO=new AccountTypeDTO(txtAccID.getText(),txtAccType.getText(),Double.parseDouble(txtInterest.getText()),Double.parseDouble(txtMinAmouOf1stDep.getText()),Integer.parseInt(cmbCountOfOwners.getValue()));
        boolean isDelete=false;

        try {
            isDelete = accountTypeService.deleteAccountType(accountTypeDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isDelete){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Deleted..",ButtonType.OK);
            alert.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Delete Faild",ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    public void clearAccountDetais(MouseEvent mouseEvent) {
    }

    public void getAllAccountType(){
        accDetailsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("accTypeID"));
        accDetailsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("accType"));
        accDetailsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("rate"));
        accDetailsTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("minAmouOf1stDep"));
        accDetailsTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("countOfOwners"));

        try {
            accDetailsTable.setItems(FXCollections.observableArrayList(accountTypeService.getAllAccountType()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadTableToTXT() throws Exception{
        accDetailsTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends AccountTypeDTO> observable, AccountTypeDTO oldValue, AccountTypeDTO newValue) -> {
            AccountTypeDTO values= observable.getValue();
            txtAccID.setText(values.getAccTypeID());
            txtAccType.setText(values.getAccType());
            txtInterest.setText(Double.toString(values.getRate()));
            txtMinAmouOf1stDep.setText(Double.toString(values.getMinAmouOf1stDep()));
            cmbCountOfOwners.setValue(Integer.toString(values.getCountOfOwners()));
        });
    }

    private void accountTypeIDLoader(){

        String id;
        try {
            id = IDGenarator.getNewID("accounttype", "accTypeID_PK", "ACC-");
            txtAccID.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //Loan type--------------------------------------------------------------------------------------------------------
    @FXML
    public void updateLoanType(MouseEvent mouseEvent) {
    }

    @FXML
    public void saveLoanType(MouseEvent mouseEvent) {
    }

    @FXML
    public void searchLoanType(MouseEvent mouseEvent) {
    }

    @FXML
    public void deleteLoanType(MouseEvent mouseEvent) {
    }

    @FXML
    public void clearLoanDetails(MouseEvent mouseEvent) {
    }


    @Override
    public void update(String message) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(()->{

                });
            }
        }).start();
    }

    @FXML
    public void reserve(ActionEvent actionEvent) throws Exception {
        AccountTypeDTO typeDTO=new AccountTypeDTO();
        typeDTO.setAccTypeID(txtAccID.getText());
        accountTypeService.reserved(typeDTO);
    }
}
