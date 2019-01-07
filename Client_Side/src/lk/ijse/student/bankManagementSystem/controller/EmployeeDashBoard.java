package lk.ijse.student.bankManagementSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeDashBoard implements Initializable {

    @FXML
    private AnchorPane mainDashBoard;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDAteTime();
    }

    @FXML
    public void minimise(MouseEvent mouseEvent) {
        Stage stage  = (Stage) mainDashBoard.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void reportPaneLoad(MouseEvent mouseEvent) {
        try {
            AnchorPane an = FXMLLoader.load(this.getClass().getResource("/lk/ijse/student/bankManagementSystem/views/ReportPage.fxml"));
            mainDashBoard.getChildren().add(an);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void homeButtonIsClick(MouseEvent mouseEvent) {
    }

    @FXML
    public void logOut(MouseEvent mouseEvent) {
        Stage stage  = (Stage) mainDashBoard.getScene().getWindow();
        Alert a=new Alert(Alert.AlertType.INFORMATION, "Are you Sure, do you want LogOut in Bank Management System?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result=a.showAndWait();
        if (result.get()==ButtonType.YES){
            stage.close();
        }else{
            a.close();
        }
    }

    @FXML
    public void openAccButtonIsClick(MouseEvent mouseEvent) {
        try {
            AnchorPane an = FXMLLoader.load(this.getClass().getResource("/lk/ijse/student/bankManagementSystem/views/employeeView/OpenAccount.fxml"));
            mainDashBoard.getChildren().add(an);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void depositeButtonIsClick(MouseEvent mouseEvent) {
        try {
            AnchorPane an = FXMLLoader.load(this.getClass().getResource("/lk/ijse/student/bankManagementSystem/views/employeeView/MoneyDepositAndWithdraw.fxml"));
            mainDashBoard.getChildren().add(an);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void setDAteTime(){
        Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                txtDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
                txtTime.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
            }
        }),new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    @FXML
    public void manageCustomerEvent(MouseEvent mouseEvent) {
    }
}
