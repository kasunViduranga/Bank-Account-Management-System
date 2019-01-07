package lk.ijse.student.bankManagementSystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    private String accNum_PK;
    private String openDate;
    private String openTime;
    private double total;
    private String nic;
    @ManyToOne(cascade = CascadeType.ALL)
    AccountType accountType;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    List<CustomerAccountDetail>customerAccountDetails=new ArrayList<>();
    public Account() {
    }

    public Account(String accNum_PK, String openDate, String openTime, double total, String nic, AccountType accountType, List<CustomerAccountDetail> customerAccountDetails) {
        this.accNum_PK = accNum_PK;
        this.openDate = openDate;
        this.openTime = openTime;
        this.total = total;
        this.nic = nic;
        this.accountType = accountType;
        this.customerAccountDetails = customerAccountDetails;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<CustomerAccountDetail> getCustomerAccountDetails() {
        return customerAccountDetails;
    }


    public void setCustomerAccountDetails(List<CustomerAccountDetail> customerAccountDetails) {
        this.customerAccountDetails = customerAccountDetails;
    }

    public Account(String accNum_PK, String openDate, String openTime, double total, String nic, AccountType accountType) {
        this.accNum_PK = accNum_PK;
        this.openDate = openDate;
        this.openTime = openTime;
        this.total = total;
        this.nic = nic;
        this.accountType = accountType;
    }

    public String getAccNum_PK() {
        return accNum_PK;
    }

    public void setAccNum_PK(String accNum_PK) {
        this.accNum_PK = accNum_PK;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accNum_PK='" + accNum_PK + '\'' +
                ", openDate='" + openDate + '\'' +
                ", openTime='" + openTime + '\'' +
                ", total=" + total +
                ", nic='" + nic + '\'' +
                ", accountType=" + accountType +
                ", customerAccountDetails=" + customerAccountDetails +
                '}';
    }
}
