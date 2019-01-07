package lk.ijse.student.bankManagementSystem.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lk.ijse.student.bankManagementSystem.dto.*;
import lk.ijse.student.bankManagementSystem.idGenarator.IDGenarator;
import lk.ijse.student.bankManagementSystem.proxy.ProxyHandeler;
import lk.ijse.student.bankManagementSystem.service.ServiceFactory;
import lk.ijse.student.bankManagementSystem.service.custom.AccountService;
import lk.ijse.student.bankManagementSystem.service.custom.AccountTypeService;
import  lk.ijse.student.bankManagementSystem.observer.Observer;
import lk.ijse.student.bankManagementSystem.service.custom.CustomerService;


import java.io.IOException;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenAccountController implements Initializable, Observer, Remote {

    @FXML
    private JFXTextField txt1stOwnerFirstName;

    @FXML
    private JFXTextField txt1stOwnerLastName;

    @FXML
    private JFXTextField txt1stOwnerAddress;

    @FXML
    private JFXTextField txt1stOwnerDOB;

    @FXML
    private JFXTextField txt1stOwnerNIC;

    @FXML
    private JFXTextField txt1stOwnerTell;

    @FXML
    private JFXRadioButton btn1stOwnerMale;

    @FXML
    private JFXRadioButton btn1stOwnerFemale;

    @FXML
    private JFXTextField txtAccNumber;

    @FXML
    private JFXComboBox<String> cmbAccTypeID;

    @FXML
    private TextField txtRate;

    @FXML
    private JFXTextField txtOpenTime;

    @FXML
    private JFXTextField txtOpenDate;

    @FXML
    private JFXTextField txt2ndOwnerFirstName;

    @FXML
    private JFXTextField txt2ndOwnerLastName;

    @FXML
    private JFXTextField txt2ndOwnerAddress;

    @FXML
    private JFXTextField txt2ndOwnerDOB;

    @FXML
    private JFXTextField txt2ndOwnerNIC;

    @FXML
    private JFXTextField txt2ndOwnerTell;

    @FXML
    private Label lbl2ndOwnerGender;

    @FXML
    private JFXRadioButton btn2ndOwnerMale;

    @FXML
    private JFXRadioButton btn2ndOwnerFemale;

    @FXML
    private JFXTextField txt3rdOwnerFirstName;

    @FXML
    private JFXTextField txt3rdOwnerLastName;

    @FXML
    private JFXTextField txt3rdOwnerAddress;

    @FXML
    private JFXTextField txt3rdOwnerDOB;

    @FXML
    private JFXTextField txt3rdOwnerNIC;

    @FXML
    private JFXTextField txt3rdOwnerTell;

    @FXML
    private Label lbl3rdOwnerGender;

    @FXML
    private JFXRadioButton btn3rdOwnerMale;

    @FXML
    private JFXRadioButton btn3rdOwnerFemale;

    @FXML
    private JFXTextField txtAccounType;

    @FXML
    private JFXTextField txt1stOwnerID;

    @FXML
    private JFXTextField txt2ndOwnerID;

    @FXML
    private JFXTextField txt3rdOwnerID;

    @FXML
    private RadioButton rbtn1stNewCustomer;

    @FXML
    private RadioButton rbtn2ndNewCustomer;

    @FXML
    private RadioButton rbtn3rdNewCustomer;

    @FXML
    private JFXComboBox<String> cmb1stOwnerID;

    @FXML
    private JFXComboBox<?> cmb2ndOwnerID;

    @FXML
    private JFXComboBox<?> cmb3rdOwnerID;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtCusAccDeID;

    private final ToggleGroup btnGroup1stOwner = new ToggleGroup();
    private final ToggleGroup btnGroup2ndOwner = new ToggleGroup();
    private final ToggleGroup btnGroup3rdOwner = new ToggleGroup();

    int countOfOwners;

    AccountTypeService accountTypeService;
    AccountService accountService;
    CustomerService customerService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setbuttonGroup();
            setDAteTime();
            setSelectRBTNEvent();
            accountTypeService = ProxyHandeler.getIstance().getSuperService(ServiceFactory.ServiceTypes.ACCOUNTTYPE);
            accountService = ProxyHandeler.getIstance().getSuperService(ServiceFactory.ServiceTypes.ACCOUNT);
            customerService=ProxyHandeler.getIstance().getSuperService(ServiceFactory.ServiceTypes.CUSTOMER);
            loadAccType();
            comboSelectEvent();
            UnicastRemoteObject.exportObject(this,0);
            accountTypeService.register(this);
            loadCustomerId();
            loadCustomersID();
            accountIDLoader();
            customerIDLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void createAccount(MouseEvent mouseEvent) {

        String firstOwnerGender;
        if(btn1stOwnerMale.isSelected()){
            firstOwnerGender="Male";
        }else{
            firstOwnerGender="Female";
        }

        String amount=txtAmount.getText();
        if(amount.matches("[0-9]{3,7}[.]\\d{2}")){
            txt1stOwnerFirstName.requestFocus();
            String firstName=txt1stOwnerFirstName.getText();
            if(firstName.matches("[A-Za-z]{3,30}")){
                txt1stOwnerLastName.requestFocus();
                String lastName=txt1stOwnerLastName.getText();
                if(lastName.matches("[A-Za-z]{3,30}")){
                    txt1stOwnerAddress.requestFocus();
                    String addr=txt1stOwnerAddress.getText();
                    if(addr.matches("[A-Za-z0-9]{3,30}")){
                        txt1stOwnerDOB.requestFocus();
                        String dob=txt1stOwnerDOB.getText();
                        if(dob.matches("\\d{4}\\-\\d{2}\\-\\d{2}")){
                            txt1stOwnerNIC.requestFocus();
                            String nic=txt1stOwnerNIC.getText();
                            if(nic.matches("\\d{9}[vV]")){
                                txt1stOwnerTell.requestFocus();
                                String tell=txt1stOwnerTell.getText();
                                if(tell.matches("[0-9]{10}")){

                                    //        --------------------------------------------------------------------------

                                    boolean select=rbtn1stNewCustomer.isSelected();

                                    if (select==true){
                                        AccountDTO accountDTO=new AccountDTO(txtAccNumber.getText(),txtOpenDate.getText(),txtOpenTime.getText(),
                                                Double.parseDouble(txtAmount.getText()),cmbAccTypeID.getValue(),txt1stOwnerNIC.getText());

                                        CustomerDTO customerDTO=new CustomerDTO(txt1stOwnerID.getText(), txt1stOwnerFirstName.getText(), txt1stOwnerLastName.getText(),
                                                txt1stOwnerAddress.getText(), "1st Ownner", txt1stOwnerDOB.getText(), txt1stOwnerNIC.getText(),
                                                firstOwnerGender, txt1stOwnerTell.getText());

                                        CustomerAccountDetailDTO customerAccountDetailDTO=new CustomerAccountDetailDTO(txt1stOwnerID.getText(),txtAccNumber.getText());

                                        CommenDTO commenDTO=new CommenDTO(accountDTO,customerDTO,customerAccountDetailDTO);

                                        boolean isAdd= false;
                                        try {
                                            isAdd = accountService.addAccount(commenDTO);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        if (isAdd){
                                            Alert alert=new Alert(Alert.AlertType.INFORMATION,"added",ButtonType.OK);
                                            alert.show();
                                        }else {
                                            Alert alert=new Alert(Alert.AlertType.INFORMATION,"faild",ButtonType.OK);
                                            alert.show();
                                        }
                                    }
                                    if(select==false){
                                        AccountDTO accountDTO=new AccountDTO(txtAccNumber.getText(),txtOpenDate.getText(),txtOpenTime.getText(),
                                                Double.parseDouble(txtAmount.getText()),cmbAccTypeID.getValue(),txt1stOwnerNIC.getText());
                                        CustomerAccountDetailDTO customerAccountDetailDTO=new CustomerAccountDetailDTO(cmb1stOwnerID.getValue(),txtAccNumber.getText());
                                        CommenDTO commenDTO=new CommenDTO(accountDTO,customerAccountDetailDTO);

                                        boolean isAdd= false;
                                        try {
                                            isAdd =customerService.addCustomer(commenDTO);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        if (isAdd){
                                            Alert alert=new Alert(Alert.AlertType.INFORMATION,"added",ButtonType.OK);
                                            alert.show();
                                        }else {
                                            Alert alert=new Alert(Alert.AlertType.INFORMATION,"faild",ButtonType.OK);
                                            alert.show();
                                        }
                                    }
//        ----------------------------------------


                                }else{
                                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Tell Number, Somthing Mistake", ButtonType.OK);
                                    a.showAndWait();
                                }
                            }else{
                                Alert a = new Alert(Alert.AlertType.INFORMATION, "Check NIC Number, Somthing Mistake", ButtonType.OK);
                                a.showAndWait();
                            }
                        }else{
                            Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Date of Birth, Somthing Mistake", ButtonType.OK);
                            a.showAndWait();
                        }
                    }else{
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Address, Somthing Mistake", ButtonType.OK);
                        a.showAndWait();
                    }

                }else{
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Last Name, Somthing Mistake", ButtonType.OK);
                    a.showAndWait();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Check First Name, Somthing Mistake", ButtonType.OK);
                a.showAndWait();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Check Amount, Somthing Mistake", ButtonType.OK);
            a.showAndWait();
        }

    }

    @FXML
    public void isClearAll(MouseEvent mouseEvent) {
        clearAll();
    }

    public void setbuttonGroup() throws Exception{
        btn1stOwnerMale.setToggleGroup(btnGroup1stOwner);
        btn1stOwnerFemale.setToggleGroup(btnGroup1stOwner);

        btn2ndOwnerMale.setToggleGroup(btnGroup2ndOwner);
        btn2ndOwnerFemale.setToggleGroup(btnGroup2ndOwner);

        btn3rdOwnerMale.setToggleGroup(btnGroup3rdOwner);
        btn3rdOwnerFemale.setToggleGroup(btnGroup3rdOwner);
    }

    private void setDAteTime(){
        Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                txtOpenDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
                txtOpenTime.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
            }
        }),new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    private void setSelectRBTNEvent() throws Exception {
        rbtn1stNewCustomer.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (rbtn1stNewCustomer.isSelected()) {
                    cmb1stOwnerID.setDisable(true);
                    txt1stOwnerID.setDisable(false);
                } else {
                    cmb1stOwnerID.setDisable(false);
                    txt1stOwnerID.setDisable(true);
                }
            }

        });
    }

    private void setDisabletext(){

        if(countOfOwners==1){

            rbtn2ndNewCustomer.setDisable(true);
            cmb2ndOwnerID.setDisable(true);
            txt2ndOwnerID.setDisable(true);
            txt2ndOwnerFirstName.setDisable(true);
            txt2ndOwnerLastName.setDisable(true);
            txt2ndOwnerAddress.setDisable(true);
            txt2ndOwnerDOB.setDisable(true);
            txt2ndOwnerNIC.setDisable(true);
            txt2ndOwnerTell.setDisable(true);
            btn2ndOwnerMale.setDisable(true);
            btn2ndOwnerFemale.setDisable(true);
            lbl2ndOwnerGender.setDisable(true);

            rbtn3rdNewCustomer.setDisable(true);
            cmb3rdOwnerID.setDisable(true);
            txt3rdOwnerID.setDisable(true);
            txt3rdOwnerFirstName.setDisable(true);
            txt3rdOwnerLastName.setDisable(true);
            txt3rdOwnerAddress.setDisable(true);
            txt3rdOwnerDOB.setDisable(true);
            txt3rdOwnerNIC.setDisable(true);
            txt3rdOwnerTell.setDisable(true);
            btn3rdOwnerMale.setDisable(true);
            btn3rdOwnerFemale.setDisable(true);
            lbl3rdOwnerGender.setDisable(true);

        }if(countOfOwners==2){

            rbtn2ndNewCustomer.setDisable(false);
            cmb2ndOwnerID.setDisable(false);
            txt2ndOwnerID.setDisable(false);
            txt2ndOwnerFirstName.setDisable(false);
            txt2ndOwnerLastName.setDisable(false);
            txt2ndOwnerAddress.setDisable(false);
            txt2ndOwnerDOB.setDisable(false);
            txt2ndOwnerNIC.setDisable(false);
            txt2ndOwnerTell.setDisable(false);
            btn2ndOwnerMale.setDisable(false);
            btn2ndOwnerFemale.setDisable(false);
            lbl2ndOwnerGender.setDisable(false);

            rbtn3rdNewCustomer.setDisable(true);
            cmb3rdOwnerID.setDisable(true);
            txt3rdOwnerID.setDisable(true);
            txt3rdOwnerFirstName.setDisable(true);
            txt3rdOwnerLastName.setDisable(true);
            txt3rdOwnerAddress.setDisable(true);
            txt3rdOwnerDOB.setDisable(true);
            txt3rdOwnerNIC.setDisable(true);
            txt3rdOwnerTell.setDisable(true);
            btn3rdOwnerMale.setDisable(true);
            btn3rdOwnerFemale.setDisable(true);
            lbl3rdOwnerGender.setDisable(true);

        }if(countOfOwners==3){

            rbtn2ndNewCustomer.setDisable(false);
            cmb2ndOwnerID.setDisable(false);
            txt2ndOwnerID.setDisable(false);
            txt2ndOwnerFirstName.setDisable(false);
            txt2ndOwnerLastName.setDisable(false);
            txt2ndOwnerAddress.setDisable(false);
            txt2ndOwnerDOB.setDisable(false);
            txt2ndOwnerNIC.setDisable(false);
            txt2ndOwnerTell.setDisable(false);
            btn2ndOwnerMale.setDisable(false);
            btn2ndOwnerFemale.setDisable(false);
            lbl2ndOwnerGender.setDisable(false);

            rbtn3rdNewCustomer.setDisable(false);
            cmb3rdOwnerID.setDisable(false);
            txt3rdOwnerID.setDisable(false);
            txt3rdOwnerFirstName.setDisable(false);
            txt3rdOwnerLastName.setDisable(false);
            txt3rdOwnerAddress.setDisable(false);
            txt3rdOwnerDOB.setDisable(false);
            txt3rdOwnerNIC.setDisable(false);
            txt3rdOwnerTell.setDisable(false);
            btn3rdOwnerMale.setDisable(false);
            btn3rdOwnerFemale.setDisable(false);
            lbl3rdOwnerGender.setDisable(false);
        }
    }

    private void clearAll(){
        txtAmount.clear();
        txt1stOwnerFirstName.clear();
        txt1stOwnerLastName.clear();
        txt1stOwnerAddress.clear();
        txt1stOwnerNIC.clear();
        txt1stOwnerTell.clear();
        txt1stOwnerDOB.clear();
    }

    public void loadAccType(){
        cmbAccTypeID.getItems().clear();
        List<AccountTypeDTO> accountDTOS = null;
        try {
            accountDTOS=accountTypeService.getAllAccountType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> strings=new ArrayList<>();
        for (AccountTypeDTO accountDTO:accountDTOS){
            strings.add(accountDTO.getAccTypeID());
        }
        for (String string:strings){
            cmbAccTypeID.getItems().add(string);
        }
    }

    public void loadCustomerId(){
        cmb1stOwnerID.getItems().clear();
        List<CustomerDTO> customerDTOS=null;
        try {
            customerDTOS=customerService.getAllCustomer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> strings=new ArrayList<>();
        for (CustomerDTO customerDTO:customerDTOS){
            strings.add(customerDTO.getCusID());
        }
        for (String string:strings){
            cmb1stOwnerID.getItems().add(string);
        }
    }

    private void comboSelectEvent()throws Exception{
        cmbAccTypeID.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                try {
                    String AccTypeID= cmbAccTypeID.getValue();
                    AccountTypeDTO ref= accountTypeService.searchAccountType(AccTypeID);
                    if(ref!=null){

                        txtAccounType.setText(ref.getAccType());
                        txtRate.setText(Double.toString(ref.getRate()));
                        double minAmouOf1stDep=ref.getMinAmouOf1stDep();
                        countOfOwners=ref.getCountOfOwners();
                        setDisabletext();

                    }       } catch (Exception ex) {
                    Logger.getLogger(OpenAccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    public void amountValidation(ActionEvent actionEvent) {
        String text=txtAmount.getText();
        boolean match = text.matches("[A-Za-z]+");
        if(match){
            txtAmount.requestFocus();
        }else {
            Alert alert=new Alert(Alert.AlertType.ERROR,"EX : kasun",ButtonType.OK);
            alert.show();
        }
    }

    private void accountIDLoader(){

        String id;
        try {
            id= IDGenarator.getNewID("account", "accNum_PK", "0045");
            txtAccNumber.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void customerIDLoader(){

        String id;
        try {
            id = IDGenarator.getNewID("customer", "cusID_PK", "Cus");
            txt1stOwnerID.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    loadAccType();
                });
            }
        }).start();
    }

    private void loadCustomersID()throws Exception {
        cmb1stOwnerID.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    String cusID = cmb1stOwnerID.getValue();
                    CustomerDTO ref = customerService.searchCustomer(cusID);
                    if (ref != null) {
                        txt1stOwnerFirstName.setText(ref.getFirstName());
                        txt1stOwnerLastName.setText(ref.getLastName());
                        txt1stOwnerAddress.setText(ref.getAddress());
                        txt1stOwnerNIC.setText(ref.getNic());
                        txt1stOwnerTell.setText(ref.getTell());
                        txt1stOwnerDOB.setText(ref.getDob());
                        String genders=ref.getGender();
                        btn1stOwnerMale.setSelected(genders=="Male");
                        btn1stOwnerFemale.setSelected(genders=="Female");

                    }
                } catch (Exception ex) {
                    Logger.getLogger(OpenAccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
