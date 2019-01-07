package lk.ijse.student.bankManagementSystem.dto;

public class AccountTypeDTO implements SuperDTO{

    private String  accTypeID;
    private String accType;
    private double rate;
    private double minAmouOf1stDep;
    private int countOfOwners;

    public AccountTypeDTO() {
    }

    public AccountTypeDTO(String accTypeID, String accType, double rate, double minAmouOf1stDep, int countOfOwners) {
        this.accTypeID = accTypeID;
        this.accType = accType;
        this.rate = rate;
        this.minAmouOf1stDep = minAmouOf1stDep;
        this.countOfOwners = countOfOwners;
    }

    public String getAccTypeID() {
        return accTypeID;
    }

    public void setAccTypeID(String accTypeID) {
        this.accTypeID = accTypeID;
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
}
