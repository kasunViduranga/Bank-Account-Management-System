package lk.ijse.student.bankManagementSystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    private String cusID_PK;
    private String firstName;
    private String lastName;
    private String address;
    private String ownerType;
    private String dob;
    private String nic;
    private String gender;
    private String tell;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<CustomerAccountDetail>customerAccountDetails=new ArrayList<>();

    public Customer() {
    }

    public Customer(String cusID_PK, String firstName, String lastName, String address, String ownerType, String dob, String nic, String gender, String tell, List<CustomerAccountDetail> customerAccountDetails) {
        this.cusID_PK = cusID_PK;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.ownerType = ownerType;
        this.dob = dob;
        this.nic = nic;
        this.gender = gender;
        this.tell = tell;
        this.customerAccountDetails = customerAccountDetails;
    }

    public List<CustomerAccountDetail> getCustomerAccountDetails() {
        return customerAccountDetails;
    }


    public void setCustomerAccountDetails(List<CustomerAccountDetail> customerAccountDetails) {
        this.customerAccountDetails = customerAccountDetails;
    }

    public Customer(String cusID_PK, String firstName, String lastName, String address, String ownerType, String dob, String nic, String gender, String tell) {
        this.cusID_PK = cusID_PK;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.ownerType = ownerType;
        this.dob = dob;
        this.nic = nic;
        this.gender = gender;
        this.tell = tell;
    }

    public String getCusID_PK() {
        return cusID_PK;
    }

    public void setCusID_PK(String cusID_PK) {
        this.cusID_PK = cusID_PK;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

}
