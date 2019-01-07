package lk.ijse.student.bankManagementSystem.idGenarator;

import lk.ijse.student.bankManagementSystem.DB.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IDController {
    public static String getLastID(String tableName, String colName) throws SQLException, ClassNotFoundException, IOException, Exception {
        String sql = "select " + colName + " from " + tableName + " order by 1 desc limit 1";
        Connection conn = DBConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
