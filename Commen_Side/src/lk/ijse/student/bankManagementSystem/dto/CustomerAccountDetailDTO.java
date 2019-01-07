package lk.ijse.student.bankManagementSystem.dto;

public class CustomerAccountDetailDTO implements SuperDTO {

    private String cusID;
    private String accNum;

    public CustomerAccountDetailDTO() {
    }

    public CustomerAccountDetailDTO(String cusID, String accNum) {
        this.cusID = cusID;
        this.accNum = accNum;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }
}
