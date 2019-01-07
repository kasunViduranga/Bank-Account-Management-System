package lk.ijse.student.bankManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXButton btnsubmit;

    @FXML
    private JFXPasswordField txtpassword;

    @FXML
    private JFXRadioButton rbtnAdmin;

    @FXML
    private JFXRadioButton rbtnEmployee;

    private final ToggleGroup btnGroup =new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBtnGroup();
    }

    @FXML
    public void btnClose(MouseEvent mouseEvent) {
        Stage stage= (Stage) this.mainPane.getScene().getWindow();
        Alert a=new Alert(Alert.AlertType.INFORMATION, "Are you Sure, do you want Exite on Login Form ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result=a.showAndWait();
        if (result.get()==ButtonType.YES){
            stage.close();
        }else{
            a.close();
        }
    }

    @FXML
    public void submitButtonAction(ActionEvent actionEvent) throws IOException {
        if (rbtnAdmin.isSelected()){
            Parent root= FXMLLoader.load(this.getClass().getResource("/lk/ijse/student/bankManagementSystem/views/addminView/DashBoard.fxml"));
            Scene tempScene=new Scene(root);
            Stage stage= (Stage) this.mainPane.getScene().getWindow();
            stage.setScene(tempScene);
            stage.centerOnScreen();
        }if (rbtnEmployee.isSelected()){
            Parent root= FXMLLoader.load(this.getClass().getResource("/lk/ijse/student/bankManagementSystem/views/employeeView/DashBoard.fxml"));
            Scene tempScene=new Scene(root);
            Stage stage= (Stage) this.mainPane.getScene().getWindow();
            stage.setScene(tempScene);
            stage.centerOnScreen();
        }else if(!rbtnAdmin.isSelected() && !rbtnEmployee.isSelected()){
            Alert a=new Alert(Alert.AlertType.ERROR,"Select User Type.......!", ButtonType.OK);
            a.showAndWait();
        }
    }

    private void setBtnGroup(){
        rbtnAdmin.setToggleGroup(btnGroup);
        rbtnEmployee.setToggleGroup(btnGroup);
    }


}
