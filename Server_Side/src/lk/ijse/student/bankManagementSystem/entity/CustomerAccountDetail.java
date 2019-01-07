package lk.ijse.student.bankManagementSystem.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerAccountDetail implements Serializable {
    @Id
    @ManyToOne(cascade ={CascadeType.ALL} )
    Customer customer;
    @Id
    @ManyToOne(cascade ={CascadeType.ALL} )
    Account account;
    @OneToMany(mappedBy = "customerAccountDetail",cascade = CascadeType.ALL)
    List<Transaction>transactions=new ArrayList<>();
    public CustomerAccountDetail() {
    }
//
//    public CustomerAccountDetail(String cusAccDetailsID_PK, String cusID_FK, String accNum_FK, Customer customer, Account account, List<Transaction> transactions) {
//        this.cusAccDetailsID_PK = cusAccDetailsID_PK;
//        this.cusID_FK = cusID_FK;
//        this.accNum_FK = accNum_FK;
//        this.customer = customer;
//        this.account = account;
//        this.transactions = transactions;
//    }

    public CustomerAccountDetail(Customer customer, Account account, List<Transaction> transactions) {
        this.customer = customer;
        this.account = account;
        this.transactions = transactions;
    }

    public CustomerAccountDetail(Customer customer, Account account) {
        this.customer = customer;
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "CustomerAccountDetail{" +
                "customer=" + customer +
                ", account=" + account +
                ", transactions=" + transactions +
                '}';
    }
}
