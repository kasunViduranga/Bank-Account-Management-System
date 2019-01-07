package lk.ijse.student.bankManagementSystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountType {

    @Id
    private String  accTypeID_PK;
    private String accType;
    private double rate;
    private double minAmouOf1stDep;
    private int countOfOwners;
    @OneToMany(mappedBy = "accountType",cascade = CascadeType.ALL)
    private List<Account>accounts=new ArrayList<>();

    public AccountType() {
    }

    public AccountType(String accTypeID_PK, String accType, double rate, double minAmouOf1stDep, int countOfOwners, List<Account> accounts) {
        this.accTypeID_PK = accTypeID_PK;
        this.accType = accType;
        this.rate = rate;
        this.minAmouOf1stDep = minAmouOf1stDep;
        this.countOfOwners = countOfOwners;
        this.accounts = accounts;
    }

    public AccountType(String accTypeID_PK, String accType, double rate, double minAmouOf1stDep, int countOfOwners) {
        this.accTypeID_PK = accTypeID_PK;
        this.accType = accType;
        this.rate = rate;
        this.minAmouOf1stDep = minAmouOf1stDep;
        this.countOfOwners = countOfOwners;

    }

    public String getAccTypeID_PK() {
        return accTypeID_PK;
    }

    public void setAccTypeID_PK(String accTypeID_PK) {
        this.accTypeID_PK = accTypeID_PK;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMinAmouOf1stDep() {
        return minAmouOf1stDep;
    }

    public void setMinAmouOf1stDep(double minAmouOf1stDep) {
        this.minAmouOf1stDep = minAmouOf1stDep;
    }

    public int getCountOfOwners() {
        return countOfOwners;
    }

    public void setCountOfOwners(int countOfOwners) {
        this.countOfOwners = countOfOwners;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accTypeID_PK='" + accTypeID_PK + '\'' +
                ", accType='" + accType + '\'' +
                ", rate=" + rate +
                ", minAmouOf1stDep=" + minAmouOf1stDep +
                ", countOfOwners=" + countOfOwners +
                ", accounts=" + accounts +
                '}';
    }
}
