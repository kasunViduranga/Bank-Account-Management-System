package lk.ijse.student.bankManagementSystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import lk.ijse.student.bankManagementSystem.DB.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReportPageController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void loadCustomerDetailsReport(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException, JRException {
        InputStream is = this.getClass().getResourceAsStream("/lk/ijse/student/bankManagementSystem/report/customerReport.jasper");
        HashMap map = new HashMap();
        Connection connection=DBConnection.getInstance().getConnection();
        JasperPrint fillReport = JasperFillManager.fillReport(is, map, connection);
        JasperViewer.viewReport(fillReport, false);
    }

    @FXML
    public void loadAccountReport(MouseEvent mouseEvent) throws JRException, SQLException, ClassNotFoundException {
        InputStream is = this.getClass().getResourceAsStream("/lk/ijse/student/bankManagementSystem/report/AccountReport.jasper");
        HashMap map = new HashMap();
        Connection connection=DBConnection.getInstance().getConnection();
        JasperPrint fillReport = JasperFillManager.fillReport(is, map, connection);
        JasperViewer.viewReport(fillReport, false);
    }

    @FXML
    public void loadMoneyWithdrawDetailsReport(MouseEvent mouseEvent) throws JRException, SQLException, ClassNotFoundException {
        InputStream is = this.getClass().getResourceAsStream("/lk/ijse/student/bankManagementSystem/report/withdroAndDepoReport.jasper");
        HashMap map = new HashMap();
        Connection connection=DBConnection.getInstance().getConnection();
        JasperPrint fillReport = JasperFillManager.fillReport(is, map, connection);
        JasperViewer.viewReport(fillReport, false);
    }

    @FXML
    public void loadAccTypeDetailsReport(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException, JRException {
        InputStream is = this.getClass().getResourceAsStream("/lk/ijse/student/bankManagementSystem/report/accoutTypeReport.jasper");
        HashMap map = new HashMap();
        Connection connection=DBConnection.getInstance().getConnection();
        JasperPrint fillReport = JasperFillManager.fillReport(is, map, connection);
        JasperViewer.viewReport(fillReport, false);
    }
}
