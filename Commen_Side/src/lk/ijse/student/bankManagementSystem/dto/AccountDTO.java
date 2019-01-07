package lk.ijse.student.bankManagementSystem.dto;

import lk.ijse.student.bankManagementSystem.entity.AccountType;

public class AccountDTO implements SuperDTO{
    private String accNum;
    private String openDate;
    private String openTime;
    private double total;
    private String accTypeID;
    private String nic;

    public AccountDTO() {
    }

    public AccountDTO(String accNum, String openDate, String openTime, double total, String accTypeID, String nic) {
        this.accNum = accNum;
        this.openDate = openDate;
        this.openTime = openTime;
        this.total = total;
        this.accTypeID = accTypeID;
        this.nic = nic;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAccTypeID() {
        return accTypeID;
    }

    public void setAccTypeID(String accTypeID) {
        this.accTypeID = accTypeID;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
