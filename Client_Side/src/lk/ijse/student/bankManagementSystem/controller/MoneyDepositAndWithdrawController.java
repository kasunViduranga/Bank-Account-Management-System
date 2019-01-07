package lk.ijse.student.bankManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lk.ijse.student.bankManagementSystem.dto.AccountDTO;
import lk.ijse.student.bankManagementSystem.dto.TransactionDTO;
import lk.ijse.student.bankManagementSystem.idGenarator.IDGenarator;
import lk.ijse.student.bankManagementSystem.observer.Observer;
import lk.ijse.student.bankManagementSystem.proxy.ProxyHandeler;
import lk.ijse.student.bankManagementSystem.service.ServiceFactory;
import lk.ijse.student.bankManagementSystem.service.custom.AccountService;
import lk.ijse.student.bankManagementSystem.service.custom.TransactionService;

import java.io.IOException;
import java.net.URL;
import java.rmi.Remote;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoneyDepositAndWithdrawController implements Initializable,Remote,Observer {

    @FXML
    private JFXRadioButton rbtnDeposite;

    @FXML
    private JFXRadioButton rbtnWithdraw;

    @FXML
    private JFXComboBox<String> cmbAccNumber;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtTime;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXTextField txtTRID;

    private final ToggleGroup btnGroup = new ToggleGroup();

    TransactionService transactionService;
    AccountService accountService;

    String accNum;
    String openDate;
    String openTime;
    double total;
    String accTypeID;
    String nic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            transactionService = ProxyHandeler.getIstance().getSuperService(ServiceFactory.ServiceTypes.TRANSACTION);
            accountService = ProxyHandeler.getIstance().getSuperService(ServiceFactory.ServiceTypes.ACCOUNT);
            UnicastRemoteObject.exportObject(this,0);
            accountService.register(this);
            setbuttonGroup();
            setDAteTime();
            loadAccNum();
            comboEvent();
            transactionIDLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void saveTransaction(MouseEvent mouseEvent) throws Exception{
        double oldTotal=Double.parseDouble(txtTotal.getText());
        double amount=Double.parseDouble(txtAmount.getText());
        double depositNewTotal;
        double withNewTotal;

        if (rbtnWithdraw.isSelected()){
            if (oldTotal>amount){
                withNewTotal=oldTotal-amount;
                txtTotal.setText(Double.toString(withNewTotal));

                TransactionDTO transactionDTO=new TransactionDTO(txtTRID.getText(), "Withdraw", cmbAccNumber.getValue(), Double.parseDouble(txtAmount.getText()), txtDate.getText(), txtTime.getText(), txtNIC.getText());
                boolean succes=transactionService.addTransaction(transactionDTO);

                AccountDTO accountDTO=new AccountDTO(accNum,openDate,openTime,withNewTotal,accTypeID,nic);
                succes=accountService.updateAccount(accountDTO);
                //---------------------------------------------

                if(succes){
                    Alert aa = new Alert(Alert.AlertType.INFORMATION, "Withdraw is Succesful", ButtonType.OK);
                    aa.showAndWait();
                }else{
                    Alert aa = new Alert(Alert.AlertType.INFORMATION, "Withdraw is Not Succes", ButtonType.OK);
                    aa.showAndWait();
                }
            }else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "ballence is Low, Transaction is Not sucess", ButtonType.OK);
                a.showAndWait();
            }
        }

        if (rbtnDeposite.isSelected()){
            depositNewTotal=oldTotal+amount;
            txtTotal.setText(Double.toString(depositNewTotal));

            TransactionDTO transactionDTO=new TransactionDTO(txtTRID.getText(), "Diposite", cmbAccNumber.getValue(), Double.parseDouble(txtAmount.getText()), txtDate.getText(), txtTime.getText(), txtNIC.getText());
            boolean succes=transactionService.addTransaction(transactionDTO);

            AccountDTO accountDTO=new AccountDTO(accNum,openDate,openTime,depositNewTotal,accTypeID,nic);
            succes=accountService.updateAccount(accountDTO);
            //---------------------------------------------


            if(succes){
                Alert aa = new Alert(Alert.AlertType.INFORMATION, "Deposit is Succesful", ButtonType.OK);
                aa.showAndWait();
            }else{
                Alert aa = new Alert(Alert.AlertType.INFORMATION, "Deposite is Not Succes", ButtonType.OK);
                aa.showAndWait();
            }
        }
    }

    @FXML
    public void clearLoanDetails(MouseEvent mouseEvent) {
        txtNIC.clear();
        txtTotal.clear();
        txtAmount.clear();
        txtTRID.clear();
    }

    private void setDAteTime(){
        Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                txtDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
                txtTime.setText(new SimpleDateFormat("hh:mm:ss").format(new Date()));
            }
        }),new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    public void setbuttonGroup() throws Exception{
        rbtnDeposite.setToggleGroup(btnGroup);
        rbtnWithdraw.setToggleGroup(btnGroup);
    }

    public void loadAccNum(){
        cmbAccNumber.getItems().clear();
        List<AccountDTO> accountDTOS = null;
        try {
            accountDTOS=accountService.getAllAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> strings=new ArrayList<>();
        for (AccountDTO accountDTO:accountDTOS){
            strings.add(accountDTO.getAccNum());
        }
        for (String string:strings){
            cmbAccNumber.getItems().add(string);
        }
    }



    private void comboEvent() throws Exception{
        cmbAccNumber.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    String accNumber=cmbAccNumber.getValue();
                    AccountDTO ref=accountService.searchAccount(accNumber);
                    if(ref!=null){

                        txtNIC.setText(ref.getNic());
                        txtTotal.setText(Double.toString(ref.getTotal()));

                        openDate=ref.getOpenDate();
                        openTime=ref.getOpenTime();
                        nic=ref.getNic();
                        accTypeID=ref.getAccTypeID();
                        accNum=cmbAccNumber.getValue();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MoneyDepositAndWithdrawController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void transactionIDLoader(){
        String ids;
        try {
            ids = IDGenarator.getNewID("transaction", "tranId", "TR-");
            txtTRID.setText(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String message) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    loadAccNum();
                });
            }
        }).start();
    }
}
