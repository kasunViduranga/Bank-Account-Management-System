package lk.ijse.student.bankManagementSystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

    @Id
    private String tranId;
    private String tranType;
    private String accNum;
    private double tranFee;
    private String tranDate;
    private String tranTime;
    private String tranPersNIC;
    @ManyToOne(cascade = CascadeType.ALL)
    CustomerAccountDetail customerAccountDetail;
    public Transaction() {
    }

    public Transaction(String tranId, String tranType, String accNum, double tranFee, String tranDate, String tranTime, String tranPersNIC) {
        this.tranId = tranId;
        this.tranType = tranType;
        this.accNum = accNum;
        this.tranFee = tranFee;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.tranPersNIC = tranPersNIC;
    }

    public CustomerAccountDetail getCustomerAccountDetail() {
        return customerAccountDetail;
    }

    public Transaction(String tranId, String tranType, String accNum, double tranFee, String tranDate, String tranTime, String tranPersNIC, CustomerAccountDetail customerAccountDetail) {
        this.tranId = tranId;
        this.tranType = tranType;
        this.accNum = accNum;
        this.tranFee = tranFee;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.tranPersNIC = tranPersNIC;
        this.customerAccountDetail = customerAccountDetail;
    }

    public void setCustomerAccountDetail(CustomerAccountDetail customerAccountDetail) {
        this.customerAccountDetail = customerAccountDetail;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public double getTranFee() {
        return tranFee;
    }

    public void setTranFee(double tranFee) {
        this.tranFee = tranFee;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getTranPersNIC() {
        return tranPersNIC;
    }

    public void setTranPersNIC(String tranPersNIC) {
        this.tranPersNIC = tranPersNIC;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tranId='" + tranId + '\'' +
                ", tranType='" + tranType + '\'' +
                ", accNum='" + accNum + '\'' +
                ", tranFee=" + tranFee +
                ", tranDate='" + tranDate + '\'' +
                ", tranTime='" + tranTime + '\'' +
                ", tranPersNIC='" + tranPersNIC + '\'' +
//                ", customerAccountDetail=" + customerAccountDetail +
                '}';
    }
}
